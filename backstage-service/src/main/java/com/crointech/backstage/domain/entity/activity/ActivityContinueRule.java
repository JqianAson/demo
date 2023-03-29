package com.crointech.backstage.domain.entity.activity;

import com.crointech.croincommon.common.TimeType;
import com.crointech.croincommon.util.DateUtils;
import com.crointech.croincommon.util.MathUtil;
import com.crointech.croincommon.util.StringUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason Q
 * @create 2021-10-26 20:23
 * @Description 活动规则
 **/
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ActivityContinueRule extends ActivityRule {

    private static final long serialVersionUID = 6999150153576946704L;

    /**
     * 入金有效期 -- 固定时间
     */
    private final static Integer FIXED = 1;

    /**
     * 入金有效期 -- 累计时间
     */
    private final static Integer CUMULATIVE = 2;

    //账户筛选（0：全部账号，1：主交易账号，2：同名账户）
    private Integer accountSelect;

    //入金时间类型（1：固定时间，2：累计时间）
    private Integer depositTimeModel;

    //固定入金时间
    private Date depositTimeFixed;

    //交易有效期类型（1：固定时间，2：累计时间）
    private Integer tradingValidityModel;

    //优值方案
    private Boolean freeType;

    //层级任务(例：20,50;40,100)
    private String tierTask;


    /**
     * @param credit 当前赠金
     * @return K ：需要总手数 V：可获总赠金
     */
    public Map<String, String> getWillBonusAndWillVolume(BigDecimal credit) {

        Map<String, String> result = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>(1);
        Map<Integer, BigDecimal> tierMap = this.convertTierTask();

        List<Integer> tempList = Lists.newArrayList(tierMap.keySet());
        Collections.sort(tempList);
        Integer key = tempList.get(0);
        BigDecimal value = tierMap.get(key);
        if (MathUtil.equal(credit, BigDecimal.ZERO) || MathUtil.lessThan(credit, value)) {
            result.put("0", "0");
            return result;
        }

        for (Map.Entry<Integer, BigDecimal> entry : tierMap.entrySet()) {
            if (MathUtil.isGreaterThanAndEqual(credit, entry.getValue())) {
                result.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }

        String max = Collections.max(result.keySet());
        for (Map.Entry<String, String> entry : result.entrySet()) {
            if (entry.getKey().contains(max)) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }

    /**
     * 获取下一层级交易手数
     *
     * @param tradedVolume 当前账号交易手数
     * @return 下一层级交易手数
     */
    public BigDecimal getNextLevelLots(BigDecimal tradedVolume) {

        Map<Integer, BigDecimal> tierMap = this.convertTierTask();
        if (!CollectionUtils.isEmpty(tierMap)) {
            List<Integer> tempList = Lists.newArrayList(tierMap.keySet());
            Collections.sort(tempList);
            for (Integer volume : tempList) {
                if (MathUtil.lessThan(tradedVolume, BigDecimal.valueOf(volume))) {
                    return BigDecimal.valueOf(volume);
                }
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * 通过交易手数获取层级任务配置的赠金金额
     *
     * @param volume 交易手数
     * @return 赠金金额
     */
    public BigDecimal getTierCredit(Integer volume) {
        BigDecimal credit = null;
        if (!StringUtil.isBlank(this.tierTask)) {
            String[] tierTaskList = this.tierTask.split(";");
            Map<Integer, BigDecimal> tierMap = new HashMap<>();
            for (String t : tierTaskList) {
                String[] credits = t.split(",");
                if (tierMap.containsKey(Integer.valueOf(credits[0]))) {
                    continue;
                }
                tierMap.put(Integer.valueOf(credits[0]), new BigDecimal(credits[1]));
            }
            List<Integer> tempList = Lists.newArrayList(tierMap.keySet());
            Collections.sort(tempList);
            for (Integer tierVolume : tempList) {
                if (volume.compareTo(Integer.valueOf(tierVolume)) >= 0) {
                    credit = tierMap.get(tierVolume);
                }
            }
        }
        return credit;
    }

    /**
     * 转换层级任务为Map
     *
     * @return 转换后的层级任务
     */
    public Map<Integer, BigDecimal> convertTierTask() {
        if (!StringUtil.isBlank(this.tierTask)) {
            String[] tierTaskList = this.tierTask.split(";");
            Map<Integer, BigDecimal> tierMap = new HashMap<>();
            for (String t : tierTaskList) {
                String[] credits = t.split(",");
                if (tierMap.containsKey(Integer.valueOf(credits[0]))) {
                    continue;
                }
                tierMap.put(Integer.valueOf(credits[0]), new BigDecimal(credits[1]));
            }

            if (!CollectionUtils.isEmpty(tierMap)) {
                Map<Integer, BigDecimal> result = new HashMap<>(tierMap.size());
                tierMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
                return result;
            }
        }
        return null;
    }

    /**
     * 校验交易手数是否满足层级
     *
     * @param volume 交易手数
     * @return 校验结果
     */
    public Boolean checkTierConditions(BigDecimal volume) {

        if (!StringUtil.isBlank(this.tierTask)) {
            String[] tierTaskList = this.tierTask.split(";");
            List<Integer> tempList = new ArrayList<>();
            for (String t : tierTaskList) {
                String[] credits = t.split(",");
                if (tempList.contains(Integer.valueOf(credits[0]))) {
                    continue;
                }
                tempList.add(Integer.valueOf(credits[0]));
            }
            Collections.sort(tempList);
            for (Integer tierVolume : tempList) {
                if (volume.compareTo(BigDecimal.valueOf(tierVolume)) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 计算累计交易时间
     *
     * @param initTime    订单创建时间
     * @param defaultTime 默认时间（入金累计时间）
     * @return 累计交易时间
     */
    public Date calculateCumulativeTradeTime(Date initTime, Date defaultTime) {
        if (FIXED.equals(this.getTradingValidityModel())) {
            //固定交易有效期时间计算
            return DateUtils.getCumulativeTime(initTime, this.getTradingValidityDay(), TimeType.DAY.getId());
        } else if (CUMULATIVE.equals(this.getTradingValidityModel())) {
            //累计交易有效期
            //累计交易有效期与累计入金时间一致
            return defaultTime;
        } else {
            log.info("unknown cumulative volume time type");
        }
        return null;
    }

    /**
     * 计算累计入金时间
     *
     * @param defaultTime 默认时间（首次入金时间 || 订单创建时间）
     * @return 累计时间
     */
    public Date calculateCumulativeDepositTime(Date defaultTime) {
        if (FIXED.equals(this.getDepositTimeModel())) {
            //固定时间
            return this.getDepositTimeFixed();
        } else if (CUMULATIVE.equals(this.getDepositTimeModel())) {
            //累计时间
            Integer time = this.getDepositTime();
            return DateUtils.getCumulativeTime(defaultTime, time, this.getDepositTimeType());
        } else {
            log.info("unknown deposit time type");
        }
        return null;
    }
//
//    /**
//     * 赠金变更后校验当前层级
//     *
//     * @param accountInfo 交易账号信息
//     * @param type        业务类型（详情见Enum：BusinessType）
//     */
//    public void checkTierConditionsChange(ActivityAccount accountInfo, BusinessType type) {
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//        ActivityAccountRepository repository = factory.createActivityAccountRepository();
//        LockExecutor.execute(LockExecutor.getLockKey("condition_change_" + accountInfo.getMerchantsId() +
//                accountInfo.getActivityId() + accountInfo.getTradingAccount()), () -> {
//            ActivityAccount account = repository.loadAccountInfo(accountInfo.getMerchantsId(), accountInfo.getId());
//            if (null != account && account.getCanBalance() && account.getAccountStatus()) {
//                Mt4AccountInfo mt4AccountInfo = commonDomainService.loadMt4UserInfo(account.getMerchantsId(), account.getServerId(), account.getTradingAccount());
//                if (null != mt4AccountInfo) {
//                    // 未修改过可提取金额
//                    if (!account.getCanBalanceUpdate()) {
//                        HashMap<String, String> param = new HashMap<>(5);
//                        if (this.getFreeType()) {
//                            BigDecimal tierCredit = this.getTierCredit(account.getTradedVolume().intValue());
//                            BigDecimal sendCredit = MathUtil.lessThanAndEqual(tierCredit, mt4AccountInfo.getCredit())
//                                    ? tierCredit : mt4AccountInfo.getCredit();
//                            if (null != tierCredit) {
//                                param.put("tierCredit", tierCredit.toString());
//                            }
//                            param.put("mt4 account credit", mt4AccountInfo.getCredit().toString());
//                            account.setCanBalanceAmount(sendCredit);
//                        }
//                        //增加修改记录
//                        param.put("accountVolume", account.getTradedVolume().toString());
//                        param.put("accountAlreadyCredit", account.getAlreadyCredit().toString());
//                        param.put("tierTask", this.getTierTask());
//                        repository.updateCanBalanceAmount(account);
//                        log.info("record account:{} amount change record start", account.getTradingAccount());
//                        AmountDetail.amountChangeRecord(account, type, JSON.toJSONString(param));
//                        log.info("record account:{} amount change record successful", account.getTradingAccount());
//                    }
//                }
//            }
//        });
//    }
}
