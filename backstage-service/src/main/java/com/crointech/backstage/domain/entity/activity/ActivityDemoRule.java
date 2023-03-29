package com.crointech.backstage.domain.entity.activity;

import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason Q
 * @create 2022-09-23 15:18
 * @Description Demo活动规则实体
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDemoRule extends BaseEntity {

    private String activityId;

    /**
     * 账户类型（0：ALL，2：STP，1：ECN）
     */
    private Integer royaltyType;

    /**
     * 账号类型（0：新账号，1：老账号，2：新老账号）
     */
    private Integer userType;

    /**
     * 账户筛选（0：全部账号，1：主交易账号，2：同名账户）
     */
    private Integer accountSelect;

    /**
     * 代理类型 1、全部人员 2、指定代理）
     */
    private Integer agentType;

    /**
     * 代理筛选类型（0：参与，1：排除）
     */
    private Integer screeningType;

    /**
     * 返佣账号集合
     */
    private List<Integer> commissionAccount;

    /**
     * 1:自动报名/手动报名
     * 2：入金类型
     * 3：入金时间（入金有效期）
     * 4：最小入金金额
     * 5：demo账号下发赠金
     * 6：真实账号交易完成手数类型（百分比和固定数字）
     * 7：真实账号完成交易手数数量
     * 8：交易有效期
     * 9：demo账号释放标准
     * 10：释放金额类型（固定金额和demo盈利）
     * 11：释放demo盈利上限
     * 12：释放类型
     * 13：品种组
     * 14：活动结束标识
     */

    /**
     * 报名方式（1：自动报名，2：手动报名，当前活动默认手动报名）
     */
    private Integer applyType;

    /**
     * 入金类型 1：首次单笔，2：累计入金，3：单笔限额
     */
    private Integer depositType;

    /**
     * 累计入金时间单位（1：小时，2：天）
     */
    private Integer depositTimeType;

    /**
     * 累计入金时间
     */
    private Integer depositTime;

    /**
     * 最低入金金额（保留两位小数）
     */
    private BigDecimal minimumDepositAmount;

    /**
     * 账号报名后Demo账号下发赠金数量
     */
    private BigDecimal sendDemoCredit;

    /**
     * 真实账号完成交易手数类型（1:固定手数，2:比例手数）
     */
    private Integer tradingNumberType;

    /**
     * 真实账号完成交易手数
     */
    private BigDecimal tradingNumberProportion;

    /**
     * 交易有效期（此字段默认单位为:天）
     */
    private Integer tradingValidityDay;

    /**
     * 释放类型（1：自动释放，2：人工释放，当前活动默认自动释放）
     */
    private Integer freeType;

    /**
     * 释放标准类型（1：净值，2：余额）
     */
    private Integer freeStandardType;

    /**
     * 释放标准对应金额
     */
    private BigDecimal freeStandardAmount;

    /**
     * 释放金额类型 (1:固定金额 ，2:demo账号当前balance)
     */
    private Integer freeAmountType;

    /**
     * 释放Demo当前Balance上限
     */
    private BigDecimal freeAmountCeiling;

    /**
     * 释放金额
     */
    private BigDecimal freeAmount;

    /**
     * demo账号盈利类型（1：固定上限，2：无上限）
     */
    private Integer profitOfDemoType;


    /**
     * 交易组类型（0：筛选交易组，1：全部交易组（全部交易组代表所有server下的所有交易组））
     */
    private Integer tradingGroupType;

    /**
     * 交易组筛选类型（0：参与，1：排除）
     */
    private Integer tradingGroupScreeningType;

    /**
     * 活动结束标识 1、活动有效期结束 2、交易手数完成 3、出金或转账（转出）
     */
    private List<Integer> endIdentity;

    /**
     * 开通demo账号默认交易组
     */
    private String demoAccountDefaultGroup;

//
//    /**
//     * 计算释放金额
//     *
//     * @param demoAccount 账号信息
//     * @return 释放金额
//     */
//    public BigDecimal calculateReleaseAmount(ActivityDemoAccount demoAccount) {
//        //释放金额类型
//        Integer freeAmountType = this.getFreeAmountType();
//        BigDecimal result = BigDecimal.ZERO;
//
//        //固定
//        Integer fixedAmountType = 1;
//        //当前demo账号balance
//        Integer currentDemoAccountBalanceType = 2;
//
//        //固定金额
//        if (freeAmountType.equals(fixedAmountType)) {
//            //固定金额
//            result = this.getFreeAmount();
//        }
//
//        //当前demo账号balance
//        if (freeAmountType.equals(currentDemoAccountBalanceType)) {
//            Integer profitOfDemoType = this.getProfitOfDemoType();
//            DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//            CommonDomainService commonDomainService = factory.createCommonDomainService();
//            Mt4AccountInfo mt4AccountInfo = commonDomainService.
//                loadMt4UserInfo(demoAccount.getMerchantId(), demoAccount.getServer(), demoAccount.getDemoAccount());
//            //固定上限
//            Integer fixedLimitType = 1;
//
//            //没有上限
//            Integer noUpperLimitType = 2;
//
//            if (profitOfDemoType.equals(fixedLimitType)) {
//
//                //在范围内
//                if (MathUtil.lessThanAndEqual(mt4AccountInfo.getRealBalance(), this.getFreeAmountCeiling())) {
//                    return mt4AccountInfo.getRealBalance();
//                }
//
//                //固定上限
//                result = this.getFreeAmountCeiling();
//            }
//
//            if (profitOfDemoType.equals(noUpperLimitType)) {
//                //没有上限
//                result = mt4AccountInfo.getRealBalance();
//            }
//        }
//        return result;
//    }

    /**
     * 获取规则设置的真实账号交易手数
     *
     * @param demoCredit demo账号赠金
     * @return 真实账号满足规则交易手数
     */
    public BigDecimal getRulesVolume(BigDecimal demoCredit) {
        Integer tradingNumberType = this.getTradingNumberType();
        BigDecimal tradingNumber = this.getTradingNumberProportion();
        //固定手数
        if (tradingNumberType == 1) {
            return tradingNumber;
        } else {
            //比例手数
            BigDecimal proportion = tradingNumber.divide(BigDecimal.valueOf(100));
            return demoCredit.multiply(proportion);
        }
    }
}
