package com.crointech.backstage.domain.entity.activityaccount;

import com.crointech.croincommon.bean.BaseEntity;
import com.crointech.croincommon.util.DateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason Q
 * @create 2021-07-19 10:53
 * @Description 账号聚合
 **/
@Data
@Slf4j
public class ActivityAccount extends BaseEntity {

    private static final long serialVersionUID = -8205042436028237209L;

    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户ID
     */
    private Integer merchantsId;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 客户英文姓名
     */
    private String userName;

    /**
     * 客户中文姓名
     */
    private String cnName;

    /**
     * 国家/地区
     */
    private String country;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 交易账号
     */
    private Integer tradingAccount;

    /**
     * 账户状态
     */
    private Boolean accountStatus;

    /**
     * 报名活动时间
     */
    private Date applyActivityTime;

    /**
     * 首次入金时间
     */
    private Date firstDepositTime;

    /**
     * 活动期间累计入金
     */
    private BigDecimal cumulativeDeposit = BigDecimal.ZERO;

    /**
     * 应得总赠金金额
     */
    private BigDecimal creditSum = BigDecimal.ZERO;

    /**
     * 待发送赠金金额
     */
    private BigDecimal waitingCredit = BigDecimal.ZERO;

    /**
     * 已发送赠金金额
     */
    private BigDecimal alreadyCredit = BigDecimal.ZERO;

    /**
     * 活动期间累计交易手数
     */
    private BigDecimal tradedVolume = BigDecimal.ZERO;

    /**
     * 交易手数累计结束时间
     */
    private Date cumulativeVolumeTime;

    /**
     * 是否可以提取赠金
     */
    private Boolean canBalance;

    /**
     * 赠金可提取金额
     */
    private BigDecimal canBalanceAmount = BigDecimal.ZERO;

    /**
     * 可提取金额修改标识
     */
    private Boolean canBalanceUpdate;

    /**
     * 累计入金时间
     */
    private Date cumulativeDepositTime;

    /**
     * 交易组所属ServerId
     */
    private Integer serverId;

    /**
     * 备注
     */
    private String remark;

    /**
     * MT4设置赠金有效期
     */
    private Integer days = 999;

    /**
     * 来源 1、活动报名 2、特批报名
     */
    private Integer source;

    /**
     * 可操作金额（计算时使用）
     */
    private BigDecimal operAmount = BigDecimal.ZERO;

    /**
     * 可操作金额（计算时使用）
     */
    private BigDecimal profitAmt = BigDecimal.ZERO;

    /**
     * 可操作金额（计算时使用）
     */
    private BigDecimal superProfitAmt = BigDecimal.ZERO;

    /**
     * 最后一次操作人
     */
    private Integer optUser;

    /**
     * 最后一次操作时间
     */
    private Date optTime;

    /**
     * 修改备注
     *
     * @param userId 当前登陆人姓名
     * @param remark 备注参数
     */
    public void editRemark(Integer userId, String remark) {
        this.remark = remark;
        this.setOptUser(userId);
        this.setOptTime(DateUtils.getCurDate());
    }

    /**
     * 编辑账户状态
     *
     * @param status   状态参数
     * @param userId   当前操作人
     * @param userName 当前用户姓名
     */
    public void editAccountStatus(Boolean status, Integer userId, String userName) {
        this.accountStatus = status;
        this.setOptTime(DateUtils.getCurDate());
        this.setOptUser(userId);
        this.remark = setOptRemark(userName, new Date());
    }

    /**
     * 请求接口修改账户状态时需要自动修改备注
     *
     * @param userName    当前登陆人姓名
     * @param currentDate 当前时间
     * @return 备注
     */
    public String setOptRemark(String userName, Date currentDate) {
        return userName + "  revised account status in  " + DateUtils.dateToString(currentDate);
    }
//
//    /**
//     * myaccount报名
//     *
//     * @param req
//     * @param tradingAccount
//     */
//    public void add(ApplyReq req, TradingAccountReq tradingAccount) {
//        addCommon(req.getActivityId());
//        this.setServerId(tradingAccount.getServerId());
//        this.setTradingAccount(tradingAccount.getTradingAccount());
//        this.setMerchantsId(req.getMerchantsId());
//        this.setUserId(req.getUserId());
//        this.setUserName(req.getUserName());
//        this.setCnName(req.getCnName());
//        this.setCountry(req.getCountry());
//        this.setPhone(req.getPhone());
//        this.setEmail(req.getEmail());
//        this.setApplyActivityTime(new Date());
//        this.setFirstDepositTime(null);
//        this.setCumulativeDeposit(BigDecimal.ZERO);
//        this.setCreditSum(BigDecimal.ZERO);
//        this.setWaitingCredit(BigDecimal.ZERO);
//        this.setAlreadyCredit(BigDecimal.ZERO);
//        this.setTradedVolume(BigDecimal.ZERO);
//        this.setSource(AccountSourceType.ACTIVITY_APPLY.getId());
//    }
//
//    /**
//     * 特批报名
//     *
//     * @param merchantsId           商户
//     * @param req                   请求参数
//     * @param volume                交易手数
//     * @param tradeAccountInfo      交易账号
//     * @param cumulativeVolumeTime  交易手数累计结束时间
//     * @param cumulativeDepositTime 累计入金时间
//     * @param activityId            活动id
//     * @param userId
//     */
//    public void addSpecialApply(Integer merchantsId, SpecialApplyReq req, BigDecimal volume, TradeAccountInfo tradeAccountInfo,
//                                Date cumulativeVolumeTime, Date cumulativeDepositTime, String activityId, Integer userId) {
//        addCommon(activityId);
//        this.setServerId(tradeAccountInfo.getServerId());
//        this.setTradingAccount(tradeAccountInfo.getTradingAccount());
//        //this.setOptUser(tradeAccountInfo.getUserId());
//        this.setOptUser(userId);
//        this.setMerchantsId(merchantsId);
//        this.setUserId(tradeAccountInfo.getUserId());
//        this.setUserName(tradeAccountInfo.getUserName());
//        this.setCnName(tradeAccountInfo.getCnName());
//        this.setCountry(tradeAccountInfo.getCountry());
//        this.setPhone(tradeAccountInfo.getPhone());
//        this.setEmail(tradeAccountInfo.getEmail());
//        this.setCreditSum(req.getCreditSum());
//        this.setTradedVolume(volume);
//        this.setCreditSum(req.getCreditSum());
//        this.setWaitingCredit(req.getWaitingCredit());
//        this.setAlreadyCredit(req.getAlreadyCredit());
//        this.setCumulativeDeposit(req.getCumulativeDeposit());
//        this.setCumulativeDepositTime(cumulativeDepositTime);
//        this.setCumulativeVolumeTime(cumulativeVolumeTime);
//        this.setApplyActivityTime(DateUtils.stringToDate(req.getApplyActivityTime()));
//        this.setFirstDepositTime(DateUtils.stringToDate(req.getFirstDepositTime()));
//        this.setCreateUser(userId);
//        this.setSource(AccountSourceType.SPECIAL_APPLY.getId());
//    }
//
//    private void addCommon(String activityId) {
//        this.setActivityId(activityId);
//        this.setAccountStatus(Boolean.TRUE);
//        this.setCanBalance(Boolean.FALSE);
//        this.setCanBalanceAmount(BigDecimal.ZERO);
//        this.setCanBalanceUpdate(Boolean.FALSE);
//        this.setCreateTime(DateUtils.getCurDate());
//        this.setOptTime(DateUtils.getCurDate());
//        this.setRemark(null);
//        this.setDel(Boolean.FALSE);
//    }
//
//    /**
//     * 特批报名
//     *
//     * @param merchantsId 商户id
//     * @param userId      用户id
//     * @param req         请求入参
//     */
//    public void specialApply(Integer merchantsId, Integer userId, SpecialApplyReq req) {
//
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//
//        LockExecutor.execute("special_apply_" + merchantsId + "_" + req.getTradingAccount(), () -> {
//            ActivityAccount activityAccount = activityAccountRepository.loadAccountInfoByAccount(req.getTradingAccount(), merchantsId);
//            if (null != activityAccount && activityAccount.getAccountStatus()) {
//                throw new BusinessException(BusinessMsg.MAAE0011);
//            }
//        });
//
//        //查询交易账号
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        TradeAccountInfo tradeAccountInfo = commonDomainService.tradingAccountInfo(merchantsId, req.getTradingAccount());
//        if (tradeAccountInfo == null) {
//            throw new BusinessException(BusinessMsg.MAAE0030);
//        }
//        if (tradeAccountInfo.getArchived()) {
//            throw new BusinessException(BusinessMsg.MAAE0072);
//        }
//        PayType payType = PayType.DEPOSIT;
//        //设置活动
//        ActivityDetailDto activityDetailDto = new ActivityDetailDto(req.getActivityId());
//        //设置规则
//        activityDetailDto.setActivityRule();
//        //设置品种组信息
//        activityDetailDto.setActivitySpecies();
//        SubType subType = activityDetailDto.getActivityTemplate().getSubType();
//        MarketingHandler handler = MarketingHandlerFactory.getMarketingHandler(subType);
//        handler.specialApply(merchantsId, userId, req, activityDetailDto, tradeAccountInfo, payType);
//    }
//
//    /**
//     * 处理消费检查净值消息
//     */
//    public void consumerCheckEquity() {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        ActivityAccountDomainService accountDomainService = factory.createActivityAccountDomainService();
//        accountDomainService.consumerCheckEquity(this);
//    }
//
//    /**
//     * 处理消费开户活动检查交易手数消息
//     */
//    public void consumerOpenActivityCheckTradeVolume() {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        ActivityAccountDomainService accountDomainService = factory.createActivityAccountDomainService();
//        accountDomainService.consumerOpenActivityCheckTradeVolume(this);
//    }
//
//    /**
//     * 处理消费Demo活动检查净值消息
//     */
//    public void consumerDemoActivityCheckTradeVolume() {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        ActivityAccountDomainService accountDomainService = factory.createActivityAccountDomainService();
//        accountDomainService.consumerDemoActivityCheckTradeVolume(this);
//    }
//
//    /**
//     * 处理消费入金活动检查交易手数消息
//     */
//    public void consumerDepositActivityCheckTradeVolume() {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        ActivityAccountDomainService accountDomainService = factory.createActivityAccountDomainService();
//        accountDomainService.consumerDepositActivityCheckTradeVolume(this);
//    }
//
//    /**
//     * 消费持续赠金活动检查交易手数消息
//     */
//    public void consumerContinueActivityCheckTradeVolume() {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        ActivityAccountDomainService accountDomainService = factory.createActivityAccountDomainService();
//        accountDomainService.consumerContinueActivityCheckTradeVolume(this);
//    }
//
//    /**
//     * 清除赠金(清除已下发赠金)
//     */
//    public BigDecimal removeCredit() {
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        CreditUpdateAccountReq req = new CreditUpdateAccountReq();
//        BigDecimal amt = MathUtil.convertNum(this.getAlreadyCredit());
//        req.setServer(this.getServerId());
//        req.setClientLogin(this.getTradingAccount());
//        req.setAmt(amt);
//        req.setDays(this.getDays());
//        req.setComment(CommonUtil.getMt4Comment(this, Mt4Comment.RECYCLING_CREDIT));
//        CreditsUpdateResp resp = commonDomainService.updateCredit(this.getMerchantsId(), req);
//        return resp.getUpdateAmount();
//    }
//
//    /**
//     * 将交易账号状态设为只读
//     */
//    public void updateAccountStateOnlyRead() {
//        // 账号状态设为只读
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        commonDomainService.setAccountStatusReadOnly(this.getMerchantsId(), this.getServerId(), this.getTradingAccount());
//    }
//
//    /**
//     * 校验交易账号是否持仓 （true：未持仓，false：持仓中）
//     *
//     * @return 校验结果
//     */
//    public Boolean checkAccountPosition() {
//        //校验用户持仓情况
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        return commonDomainService.loadAccountPosition(this.getMerchantsId(), this.getTradingAccount());
//    }
//
//    /**
//     * 校验释放标准
//     *
//     * @param activityDetail 活动规则查询体
//     * @return 校验结果
//     */
//    public Boolean checkIntoStandard(ActivityDetailDto activityDetail) {
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        ActivityOpenRule openRule = activityDetail.getActivityOpenRule();
//        AccNum01Users accountInfo = commonDomainService.loadAccNum01User(this.getServerId(), this.getMerchantsId(), this.getTradingAccount());
//        BigDecimal standardAmount = openRule.getFreeStandardAmount();
//        Integer standardType = openRule.getFreeStandardType();
//        final Integer EQUITY = 1;
//        final Integer BALANCE = 2;
//        if (EQUITY.equals(standardType)) {
//            //释放标准是净值
//            BigDecimal accountEquity = BigDecimal.valueOf(accountInfo.getEquity());
//            return accountEquity.compareTo(standardAmount) > -1;
//        } else if (BALANCE.equals(standardType)) {
//            //释放标准是余额
//            BigDecimal accountBalance = BigDecimal.valueOf(accountInfo.getBalance());
//            return accountBalance.compareTo(standardAmount) > -1;
//        } else {
//            log.warn("未知的释放标准类型");
//        }
//        return false;
//    }
//
//    /**
//     * 校验开户活动释放标准
//     *
//     * @return
//     */
//    public Boolean checkStandard() {
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        ActivityDetailDto activityDetail = new ActivityDetailDto(this.getActivityId());
//        activityDetail.setActivityRule();
//        ActivityOpenRule openRule = activityDetail.getActivityOpenRule();
//        AccNum01Users accountInfo = commonDomainService.loadAccNum01User(this.getServerId(), this.getMerchantsId(), this.getTradingAccount());
//        BigDecimal standardAmount = openRule.getFreeStandardAmount();
//        Integer standardType = openRule.getFreeStandardType();
//        final Integer EQUITY = 1;
//        final Integer BALANCE = 2;
//        if (EQUITY.equals(standardType)) {
//            //释放标准是净值
//            BigDecimal accountEquity = BigDecimal.valueOf(accountInfo.getEquity());
//            return accountEquity.compareTo(standardAmount) > -1;
//        } else if (BALANCE.equals(standardType)) {
//            //释放标准是余额
//            BigDecimal accountBalance = BigDecimal.valueOf(accountInfo.getBalance());
//            return accountBalance.compareTo(standardAmount) > -1;
//        } else {
//            log.warn("未知的释放标准类型");
//        }
//        return false;
//    }
//
//    /**
//     * 特批报名成功后发送邮件
//     *
//     * @param activity 活动信息
//     */
//    public void sendApplyMail(Activity activity) {
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        MailTemplateRepository mailTemplateRepository = factory.createMailTemplateRepository();
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService domainService = domainServiceFactory.createCommonDomainService();
//        final String defaultLanguage = "EN";
//        //拼接给报名客户邮件发送参数
//        MailTemplate clientMailTemplate = mailTemplateRepository.loadTemplate(this.getMerchantsId(),
//            MailInvokeName.CRM_ACTIVITY_REGISTER_TO_CLIENT.getInvokeName(), this.getCountry(), MailType.MAIL.getId());
//        Map<String, Object> clientParameter = new HashMap<>(2);
//
//        clientParameter.put("activityTitle", activity.getTitle());
//        clientParameter.put("detailUrl", activity.getUrl());
//
//        MailMerchantsDomainService mailMerchantsDomainService = domainServiceFactory.createMailMerchantsDomainService();
//        String clientHtml = mailMerchantsDomainService.replaceHtml(new MerchantsId(this.getMerchantsId()), this.getCountry(),
//            MailInvokeName.CRM_ACTIVITY_REGISTER_TO_CLIENT.getInvokeName(), clientParameter);
//        UserTraderInfo clientUserInner = domainService.queryUserTradeInfoByUserId(this.getMerchantsId(), this.getUserId());
//        SendMailReq clientMailReq = new SendMailReq().convertSendReq(clientUserInner.getLoginEmail(), this.getUserId(),
//            clientMailTemplate, clientHtml);
//        //给报名客户发送邮件
//        List<MailResponse> clientResponse = domainService.sendMail(this.getMerchantsId(), clientMailReq);
//        if (!CollectionUtils.isEmpty(clientResponse) && clientResponse.get(0).getSuccess()) {
//            log.info("账号:{} 特批报名成功后，邮件通知客户完成", this.getTradingAccount());
//        }
//
//        //拼接给报名客户上级销售邮件发送参数
//        String salesEmail = domainService.querySalesEmailByTradeAccount(this.getMerchantsId(), this.getTradingAccount());
//        if (StringUtil.isEmpty(salesEmail)) {
//            log.info("当前账号:{} 未分配上级销售", this.getTradingAccount());
//        } else {
//            MailTemplate salesMailTemplate = mailTemplateRepository.loadTemplate(this.getMerchantsId(),
//                MailInvokeName.CRM_ACTIVITY_REGISTER_TO_SALE.getInvokeName(), defaultLanguage,
//                MailType.MAIL.getId());
//            Map<String, Object> salesParameter = new HashMap<>(2);
//            //填充替换变量
//            salesParameter.put("account", this.getTradingAccount());
//            salesParameter.put("activityTitle", activity.getTitle());
//            //替换完整模版
//            String salesHtml = mailMerchantsDomainService.replaceHtml(new MerchantsId(this.getMerchantsId()), defaultLanguage,
//                MailInvokeName.CRM_ACTIVITY_REGISTER_TO_SALE.getInvokeName(), salesParameter);
//
//            SendMailReq salesMailReq = new SendMailReq().convertSendReq(salesEmail, null, salesMailTemplate, salesHtml);
//            //给报名客户发送邮件
//            List<MailResponse> salesResponse = domainService.sendMail(this.getMerchantsId(), salesMailReq);
//            if (!CollectionUtils.isEmpty(salesResponse) && salesResponse.get(0).getSuccess()) {
//                log.info("账号:{} 特批报名成功后邮件通知上级销售完成", this.getTradingAccount());
//            }
//        }
//    }
//
//    /**
//     * 校验账号交易手数是否满足
//     *
//     * @return 校验结果
//     */
//    public boolean checkTradeVolume() {
//        ActivityDetailDto activityDetail = new ActivityDetailDto(this.getActivityId());
//        ActivityTemplate activityTemplate = activityDetail.getActivityTemplate();
//        activityDetail.setActivityRule();
//        BigDecimal tradeNumber = BigDecimal.ZERO;
//        SubType subType = activityTemplate.getSubType();
//        if (subType == SubType.OPEN_PHYSICAL) {
//            log.info("当前账号:{} 参与活动类型为 开户赠金 类型活动", this.getTradingAccount());
//            ActivityOpenRule openRule = activityDetail.getActivityOpenRule();
//            //活动规则交易手数
//            tradeNumber = BigDecimal.valueOf(openRule.getTradingNumberProportion());
//            //当前交易账号的交易手数必须大于等于规定手数，并且交易手数必须大于0
//            return this.getTradedVolume().compareTo(tradeNumber) > -1 && this.getTradedVolume().compareTo(BigDecimal.ZERO) > 0;
//        } else if (subType == SubType.DEPOSIT_WITHGOLD) {
//            log.info("当前账号:{} 参与活动类型为 入金赠金 类型活动", this.getTradingAccount());
//            ActivityRule activityRule = activityDetail.getActivityRule();
//            //活动规则交易手数比例
//            tradeNumber = BigDecimal.valueOf(activityRule.getTradingNumberProportion())
//                .divide(BigDecimal.valueOf(100));
//            //计算账号应满足交易手数
//            BigDecimal volume = this.getCreditSum().multiply(tradeNumber);
//            //当前交易账号的交易手数必须大于等于规定手数，并且交易手数必须大于0
//            return this.getTradedVolume().compareTo(volume) > -1 && this.getTradedVolume().compareTo(BigDecimal.ZERO) > 0;
//        } else if (subType == SubType.DEPOSIT_CONTINUE) {
//            log.info("当前账号:{} 参与活动类型为 持续赠金 类型活动", this.getTradingAccount());
//            ActivityContinueRule activityContinueRule = activityDetail.getActivityContinueRule();
//            //当前交易账号的交易手数必须大于规定手数，并且交易手数必须大于0
//            return activityContinueRule.checkTierConditions(this.getTradedVolume());
//        } else if (subType == SubType.SUBSIDIES) {
//            //处理补贴类型活动
//            log.info("当前账号：{},参与活动类型为-补贴活动,检查交易手数不予处理", this.getTradingAccount());
//        } else if (subType == SubType.PROFIT_WITHDRAWAL) {
//            log.info("当前账号:{} 参与活动类型为 可盈利出金 类型活动", this.getTradingAccount());
//            ActivityProfitRule profitRule = activityDetail.getActivityProfitRule();
//            //活动规则交易手数比例
//            tradeNumber = BigDecimal.valueOf(profitRule.getTradingNumberProportion())
//                .divide(BigDecimal.valueOf(100));
//            //计算账号应满足交易手数
//            BigDecimal volume = this.getCreditSum().multiply(tradeNumber);
//            //当前交易账号的交易手数必须大于规定手数，并且交易手数必须大于0
//            return MathUtil.isGreaterThanAndEqual(this.getTradedVolume(), volume)
//                && MathUtil.isGreaterThan(this.getTradedVolume(), BigDecimal.ZERO);
//        } else {
//            log.warn("未知的活动类型");
//        }
//        return false;
//    }
//
//    /**
//     * 校验交易有效期(交易有效期存在 && 交易有效期有效)
//     *
//     * @return 校验结果
//     */
//    public Boolean checkCumulativeVolumeTime() {
//        return null != this.getCumulativeVolumeTime() && this.getCumulativeVolumeTime().getTime() >= System.currentTimeMillis();
//    }
//
//    /**
//     * 校验Demo活动释放标准是否满足
//     *
//     * @param demoRule          活动规则
//     * @param demoAccount       demo账号
//     * @param cumulativeDeposit 真实账号累计入金
//     * @return 校验结果
//     */
//    public Boolean checkDemoIntoStandard(ActivityDemoRule demoRule, ActivityDemoAccount demoAccount, BigDecimal cumulativeDeposit) {
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        AccNum01Users accountInfo = commonDomainService.loadAccNum01User(demoAccount.getServer(), demoAccount.getMerchantId(), demoAccount.getDemoAccount());
//        BigDecimal standardAmount = demoRule.getFreeStandardAmount();
//        Integer standardType = demoRule.getFreeStandardType();
//        log.info("account:{} #activityId:{} #standardType:{} #standardAmount:{}",
//            demoAccount.getDemoAccount(), demoRule.getActivityId(), standardType, standardAmount);
//        final Integer equity = 1;
//        final Integer balance = 2;
//
//        BigDecimal demoAccountEquity = BigDecimal.valueOf(accountInfo.getEquity());
//        BigDecimal demoAccountBalance = BigDecimal.valueOf(accountInfo.getBalance());
//        log.info("当前demo账号:{} #Equity:{} #balance:{}", demoAccount.getDemoAccount(), demoAccountEquity, demoAccountBalance);
//
//        //balance小于0直接返回false
//        if (MathUtil.lessThanAndEqual(demoAccountBalance, BigDecimal.ZERO)) {
//            return Boolean.FALSE;
//        }
//
//        //先判断真实账号累计入金是否满足释放标准
////        if (MathUtil.isGreaterThanAndEqual(cumulativeDeposit, demoAccountBalance)) {
//        //20230109修改为真实账号累计入金大于活动配置的最低入金门槛
//        log.info("累计入金:{} #最低入金门槛:{} #对应Demo账号:{}", cumulativeDeposit, demoRule.getMinimumDepositAmount(), demoAccount.getDemoAccount());
//        if (MathUtil.isGreaterThanAndEqual(cumulativeDeposit, demoRule.getMinimumDepositAmount())) {
//            if (equity.equals(standardType)) {
//                //释放标准是净值
//                //净值大于等于标准
//                return MathUtil.isGreaterThanAndEqual(demoAccountEquity, standardAmount);
//            } else if (balance.equals(standardType)) {
//                //释放标准是余额
//                //余额大于等于标准
//                return MathUtil.isGreaterThanAndEqual(demoAccountBalance, standardAmount);
//            } else {
//                log.warn("未知的释放标准类型");
//            }
//        }
//        return false;
//    }

    /**
     * 初始化demo活动特批数据
     */
    public void initDemoRegisterInfo() {
        this.setCreditSum(BigDecimal.ZERO);
        this.setWaitingCredit(BigDecimal.ZERO);
        this.setAlreadyCredit(BigDecimal.ZERO);
    }
}
