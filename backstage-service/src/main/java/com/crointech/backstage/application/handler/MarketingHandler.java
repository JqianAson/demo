package com.crointech.backstage.application.handler;

import com.crointech.backstage.common.dto.ActivityDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * marketing执行器模板类
 */
@Slf4j
public abstract class MarketingHandler implements InitializingBean {
//
//    @Autowired
//    private PublicService publicService;
//
//    @Autowired
//    private TransitClient transitClient;
//
//    /**
//     * 获取模板
//     *
//     * @param subType 类型
//     */
//    public void getSubType(SubType subType) {
//        log.info("MarketingHandler-subType:{}", subType);
//    }
//
//    /**
//     * 报名
//     *
//     * @param req               请求参数
//     * @param activityDetailDto 活动详情
//     * @return 报名结果
//     */
//    public List<Integer> apply(ApplyReq req, ActivityDetailDto activityDetailDto, boolean isAuto) {
//        return Lists.newArrayList();
//    }
//
//    /**
//     * 赠金报名公共逻辑
//     *
//     * @param req               报名入参
//     * @param activityDetailDto 活动详情
//     * @param royaltyType       账号类型
//     * @param userType          用户类型
//     * @return 报名失败账号
//     */
//    protected List<Integer> creditApply(ApplyReq req, ActivityDetailDto activityDetailDto, Integer royaltyType, Integer userType) {
//        ActivityAccountRepository accountRepository = RepositoryBuilder.instance().getFactory().createActivityAccountRepository();
//        List<Integer> accountList = req.getAccount().stream().map(TradingAccountReq::getTradingAccount).collect(Collectors.toList());
//        //查询报名账户
//        List<ActivityAccount> activityAccountList = accountRepository.loadAccountByAccs(req.getMerchantsId(), accountList);
//        Map<Integer, ActivityAccount> activityAccountMap = null;
//        if (!CollectionUtils.isEmpty(activityAccountList)) {
//            activityAccountMap = activityAccountList.stream().collect(Collectors.toMap(ActivityAccount::getTradingAccount, r -> r));
//        }
//        List<Integer> resultList = Lists.newArrayList();
//        List<ActivityAccount> applyAccount = Lists.newArrayList();
//        List<ActivityAccount> recyclingAccount = Lists.newArrayList();
//        for (TradingAccountReq tradingAccount : req.getAccount()) {
//            if (tradingAccount.checkAccount(activityDetailDto, royaltyType, userType)) {
//                if (activityAccountMap != null && activityAccountMap.get(tradingAccount.getTradingAccount()) != null) {
//                    ActivityAccount accountInfo = activityAccountMap.get(tradingAccount.getTradingAccount());
//                    log.info("账号:{} ,已报名", accountInfo.getTradingAccount());
//                    if (accountInfo.getAccountStatus() && req.getActivityId().equals(accountInfo.getActivityId())) {
//                        throw new BusinessException(BusinessMsg.MAAE0011);
//                    }
//                    recyclingAccount.add(accountInfo);
//                }
//
//                ActivityAccount activityAccount = new ActivityAccount();
//                activityAccount.add(req, tradingAccount);
//                applyAccount.add(activityAccount);
//                continue;
//            }
//            log.info("账号:{} #不符合报名条件", tradingAccount.getTradingAccount());
//            resultList.add(tradingAccount.getTradingAccount());
//        }
//        if (CollectionUtils.isEmpty(applyAccount) || applyAccount.size() != req.getAccount().size()) {
//            throw new BusinessException(BusinessMsg.MAAE0025);
//        }
//        LockExecutor.execute(LockExecutor.getLockKey("_activity_apply_" + req.getActivityId() + "_" + req.getUserId()), () -> {
//            //保存报名数据
//            accountRepository.batchInsert(applyAccount);
//            //处理赠金回收
//            if (!CollectionUtils.isEmpty(recyclingAccount)) {
//                this.replaceRecycling(recyclingAccount, this.getQualificationCancelRemark(), JSON.toJSONString(req));
//            }
//        });
//        return resultList;
//    }
//
//    /**
//     * 替换活动回收赠金
//     *
//     * @param accountInfoList 账号信息
//     * @param appendRemark    备注
//     * @param param           入参
//     */
//    public void replaceRecycling(List<ActivityAccount> accountInfoList, String appendRemark, String param) {
//        log.info("replaceRecycling #activityAccountList:{}", JSON.toJSONString(accountInfoList));
//        for (ActivityAccount account : accountInfoList) {
//            ActivityDetailDto detailDto = new ActivityDetailDto(account.getActivityId());
//            SubType subType = detailDto.getActivityTemplate().getSubType();
//            MarketingHandler handler = MarketingHandlerFactory.getMarketingHandler(subType);
//            handler.replaceActivityRecycling(account, appendRemark, param);
//        }
//    }
//
//    /**
//     * 替换活动回收原有账号
//     *
//     * @param account      账号
//     * @param appendRemark 备注
//     * @param param        参数
//     */
//    public abstract void replaceActivityRecycling(ActivityAccount account, String appendRemark, String param);
//
//    /**
//     * Demo赠金回收赠金
//     *
//     * @param accountInfoList 交易账号信息
//     * @param appendRemark    备注
//     * @param param           参数
//     * @param detailType      明细类型
//     * @param businessType    业务类型
//     * @param recyclingType   操作类型
//     * @param userId          操作人
//     */
//    public void demoRecycling(List<ActivityAccount> accountInfoList, String appendRemark, String param, PayType detailType,
//                              BusinessType businessType, PayType recyclingType, OperationType operationType, Integer userId) {
//        log.info("MarketingHandler #demoRecycling #account:{}", JSON.toJSONString(accountInfoList));
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        ActivityDemoAccountRepository demoAccountRepository = factory.createActivityDemoAccountRepository();
//        ActivityAccountRepository accountRepository = factory.createActivityAccountRepository();
//
//        for (ActivityAccount realAccount : accountInfoList) {
//            if (null == realAccount) {
//                continue;
//            }
//            LockExecutor.execute(LockExecutor.getLockKey("_recycling_account_" + realAccount.getTradingAccount() + "_" + realAccount.getActivityId()), () -> {
//                //查询账号关联的Demo账号信息
//                ActivityDemoAccount demoAccount = demoAccountRepository
//                    .findAccountByMerchantIdAndRealId(realAccount.getMerchantsId(), realAccount.getId());
//                String recyclingBalanceRemark = CommonUtil.getMt4Comment(realAccount, Mt4Comment.BALANCE_RECOVERY);
//                //回收Demo账号balance
//                BigDecimal recyclingBalance = commonDomainService
//                    .recyclingBalance(demoAccount.getMerchantId(), demoAccount.getServer(), demoAccount.getDemoAccount(), recyclingBalanceRemark);
//
//                //回收Demo账号回收赠金
//                CreditUpdateAccountReq updateReq = new CreditUpdateAccountReq();
//                updateReq.setServer(demoAccount.getServer());
//                updateReq.setClientLogin(demoAccount.getDemoAccount());
//                updateReq.setAmt(MathUtil.convertNum(demoAccount.getCredit()));
//                updateReq.setDays(0);
//                String recyclingCreditRemark = CommonUtil.getMt4Comment(realAccount, Mt4Comment.RECYCLING_CREDIT);
//                updateReq.setComment(recyclingCreditRemark);
//                CreditsUpdateResp creditsUpdateResp = commonDomainService.updateCredit(demoAccount.getMerchantId(), updateReq);
//                //修改Demo账号密码（密码随机）
//                ClientPasswordReq modifyPasswordReq = new ClientPasswordReq();
//                modifyPasswordReq.setClientLogin(demoAccount.getDemoAccount());
//                modifyPasswordReq.setPasswordInv(StringUtil.generateRandomDemoAccountPassword());
//                modifyPasswordReq.setPassword(StringUtil.generateRandomDemoAccountPassword());
//                transitClient.modifyPassword(demoAccount.getServer(), demoAccount.getMerchantId().toString(), modifyPasswordReq);
//                //将真实账号和Demo账号状态调整为禁用
//                demoAccountRepository.disableAccount(demoAccount);
//                realAccount.setAccountStatus(false);
//                realAccount.setRemark(StringUtil.isEmpty(realAccount.getRemark()) ? appendRemark : realAccount.getRemark() + " " + appendRemark);
//                accountRepository.editAccountStatus(realAccount);
//                //增加赠金回收流水
//                RecyclingDetail.saveDemoRecyclingRecord(realAccount.getMerchantsId(), realAccount.getActivityId(),
//                    demoAccount.getDemoAccount(), operationType, recyclingType,
//                    creditsUpdateResp.getUpdateAmount(), recyclingBalance, appendRemark, userId,
//                    DateUtils.getCurDate());
//                log.info("Demo赠金回收流水回收成功 #真实账号:{} #Demo账号:{} #回收赠金:{} #回收余额:{}", realAccount.getTradingAccount(),
//                    demoAccount.getDemoAccount(), creditsUpdateResp.getUpdateAmount(), recyclingBalance);
//
//                //记录回收明细
//                BigDecimal recyclingAmount = MathUtil.convertNum(creditsUpdateResp.getUpdateAmount());
//                UserActivityBonusDetail.saveDemoRecyclingRecord(realAccount.getMerchantsId(), realAccount.getId(),
//                    realAccount.getActivityId(), demoAccount.getDemoAccount(), recyclingAmount, userId, detailType);
//                log.info("Demo赠金回收记录回收明细成功 #真实账号:{} #Demo账号:{} #回收赠金:{}", realAccount.getTradingAccount(),
//                    demoAccount.getDemoAccount(), creditsUpdateResp.getUpdateAmount());
//
//                //保存金额修改记录
//                AmountDetail.amountChangeRecord(realAccount, businessType, param);
//                log.info("账号:{} 回收赠金成功", realAccount.getTradingAccount());
//            });
//        }
//    }
//
//    /**
//     * 开户赠金回收赠金
//     *
//     * @param accountInfoList 账号信息
//     * @param appendRemark    追加备注
//     * @param param           入参
//     */
//    public void openRecycling(List<ActivityAccount> accountInfoList, String appendRemark, String param) {
//        for (ActivityAccount accountInfo : accountInfoList) {
//            log.info("MarketingHandler #openRecycling #account:{}", JSON.toJSONString(accountInfo.getTradingAccount()));
//
//            if (null == accountInfo) {
//                continue;
//            }
//
//            if (accountInfo.getAccountStatus()) {
//                CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//                BigDecimal recyclingBalance = commonDomainService.recyclingBalance(accountInfo.getMerchantsId(), accountInfo.getServerId(),
//                    accountInfo.getTradingAccount(), CommonUtil.getMt4Comment(accountInfo, Mt4Comment.BALANCE_RECOVERY));
//                if (null != recyclingBalance) {
//                    //回收赠金
//                    BigDecimal recyclingCredit = accountInfo.removeCredit();
//                    //账号只读
//                    this.updateAccountStateOnlyRead(accountInfo);
//                    //更新营销系统账号信息
//                    this.updateMarketingAccountInfo(accountInfo, appendRemark);
//                    //给Trading发邮件
//                    this.sendMailToTrading(accountInfo);
//                    //增加赠金回收流水
//                    RecyclingDetail.saveRecyclingRecord(accountInfo, OperationType.SYSTEM, PayType.REPLACE_ACTIVITY,
//                        recyclingCredit, recyclingBalance, appendRemark, ActivityConstant.SYSTEM_ID, new Date());
//
//                    BigDecimal amt = MathUtil.convertNum(recyclingCredit);
//
//                    //记录回收明细
//                    UserActivityBonusDetail.saveCreditRecords(accountInfo, amt, ActivityConstant.SYSTEM_ID, PayType.MANUAL_RECYCLING);
//                }
//            }
//            //保存金额修改记录
//            AmountDetail.amountChangeRecord(accountInfo, BusinessType.AUTOMATIC_RECYCLING, param);
//            log.info("账号:{} 回收赠金成功", accountInfo.getTradingAccount());
//        }
//    }
//
//    /**
//     * 替换报名（回收赠金、修改现有报名数据状态、记录日志表）
//     *
//     * @param accountInfoList 回收账号
//     * @param appendRemark    备注
//     * @param param           入参
//     */
//    public void forceRecycling(List<ActivityAccount> accountInfoList, String appendRemark, String param) {
//        ActivityAccountRepository accountRepository = RepositoryBuilder.instance().getFactory().createActivityAccountRepository();
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        for (ActivityAccount accountInfo : accountInfoList) {
//
//            if (null == accountInfo) {
//                continue;
//            }
//
//            CreditsUpdateResp resp = null;
//            if (null != accountInfo.getAlreadyCredit() && accountInfo.getAlreadyCredit().compareTo(new BigDecimal(0)) > 0) {
//                BigDecimal amt = MathUtil.convertNum(accountInfo.getAlreadyCredit());
//                //开始调用CRM接口扣减赠金
//                resp = commonDomainService.updateCredit(accountInfo.getMerchantsId(), new MarketingCreditUpdateReq().
//                    convertReq(accountInfo, amt, CommonUtil.getMt4Comment(accountInfo, Mt4Comment.RECYCLING_CREDIT)));
//            }
//            accountInfo.setRemark(appendRemark);
//            accountInfo.setAccountStatus(false);
//            accountInfo.setOptUser(ActivityConstant.SYSTEM_ID);
//            accountInfo.setOptTime(new Date());
//            accountRepository.recyclingCredit(accountInfo);
//
//            if (null != resp) {
//                //增加赠金回收流水
//                RecyclingDetail.saveRecyclingRecord(accountInfo, OperationType.SYSTEM, PayType.REPLACE_ACTIVITY, resp.getUpdateAmount(), BigDecimal.ZERO,
//                    appendRemark, ActivityConstant.SYSTEM_ID, new Date());
//                //记录回收明细
//                BigDecimal recyclingAmount = MathUtil.convertNum(resp.getUpdateAmount());
//                UserActivityBonusDetail.saveCreditRecords(accountInfo, recyclingAmount, ActivityConstant.SYSTEM_ID, PayType.MANUAL_RECYCLING);
//                log.info("当前交易账号:{},赠金已回收", accountInfo.getTradingAccount());
//            }
//            //保存金额修改记录
//            AmountDetail.amountChangeRecord(accountInfo, BusinessType.AUTOMATIC_RECYCLING, param);
//            log.info("账号:{} 回收赠金成功", accountInfo.getTradingAccount());
//        }
//    }
//
//    /**
//     * 系统更新营销系统数据
//     *
//     * @param account      账号信息
//     * @param appendRemark 追加备注
//     */
//    protected void updateMarketingAccountInfo(ActivityAccount account, String appendRemark) {
//        ActivityAccountRepository accountRepository = RepositoryBuilder.instance().getFactory().createActivityAccountRepository();
//        account.setOptTime(DateUtils.getCurDate());
//        account.setOptUser(ActivityConstant.SYSTEM_ID);
//        account.setAccountStatus(false);
//        account.setRemark(appendRemark(account.getRemark(), appendRemark));
//        accountRepository.editAccountStatus(account);
//    }
//
//    /**
//     * 将交易账号状态设为只读
//     *
//     * @param account 账号信息
//     */
//    protected void updateAccountStateOnlyRead(ActivityAccount account) {
//        // 账号状态设为只读
//        CommonDomainService commonDomainService = DomainServiceBuilder.instance().getFactory().createCommonDomainService();
//        commonDomainService.setAccountStatusReadOnly(account.getMerchantsId(), account.getServerId(), account.getTradingAccount());
//    }
//
//
//    /**
//     * 回收赠金后给Trading发邮件
//     *
//     * @param activityAccount 交易账号信息
//     */
//    protected void sendMailToTrading(ActivityAccount activityAccount) {
//        try {
//            log.info("to trading Email send start account is :{}", activityAccount.getTradingAccount());
//            publicService.consumerSendOpenActivityMail(activityAccount);
//            log.info("to trading Email send end account is :{}", activityAccount.getTradingAccount());
//        } catch (Exception e) {
//            log.error("to trading Email send error account is :{}", activityAccount.getTradingAccount(), e);
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 给上级销售发送邮件
//     *
//     * @param account 交易账号信息
//     */
//    protected void sendMailToSales(ActivityAccount account) {
//        try {
//            log.info("to sales email send start, account is {}", account.getTradingAccount());
//            publicService.consumerSendDepositActivityMail(account);
//            log.info("to sales email send end, account is {}", account.getTradingAccount());
//        } catch (Exception e) {
//            log.info("to sales email send error, account is {}, error is {}", account.getTradingAccount(), e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 追加备注
//     *
//     * @param remark        原备注
//     * @param appendContent 追加备注
//     * @return 追加后的备注
//     */
//    private String appendRemark(String remark, String appendContent) {
//        if (StringUtil.isBlank(remark)) {
//            return appendContent;
//        }
//        return remark + appendContent;
//    }
//
//    /**
//     * 调用CRM将Balance清0 ，Credit清0
//     *
//     * @param account 账号信息
//     * @param userId  当前登陆人ID
//     */
//    protected void removeBalanceAndCredit(ActivityAccount account, Integer userId) {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = factory.createCommonDomainService();
//        AccNum01Users userInfo = commonDomainService.loadAccNum01User(account.getServerId(), account.getMerchantsId(), account.getTradingAccount());
//        //确认账号未被归档
//        if (null != userInfo) {
//            String comment = CommonUtil.getMt4Comment(account, Mt4Comment.BALANCE_RECOVERY);
//            //清除余额
//            BigDecimal recyclingBalance = commonDomainService.recyclingBalance(account.getMerchantsId(), account.getServerId(),
//                account.getTradingAccount(), comment);
//            if (null != recyclingBalance) {
//                //清除赠金
//                BigDecimal removeCredit = account.removeCredit();
//                //给Trading发邮件
//                sendMailToTrading(account);
//                //记录释放记录
//                BigDecimal amt = removeCredit.multiply(BigDecimal.valueOf(-1));
//                //记录回收明细
//                UserActivityBonusDetail.saveCreditRecords(account, amt, userId, PayType.INTO);
//                //记录流水
//                RecyclingDetail.saveRecyclingRecord(account, OperationType.MANUAL, PayType.MANUAL_RECYCLING, removeCredit,
//                    recyclingBalance, comment, userId, new Date());
//            }
//        }
//    }
//
//    /**
//     * 手动报名放弃原活动取消当前交易账号参与活动资格备注
//     *
//     * @return 取消活动备注信息
//     */
//    protected String getQualificationCancelRemark() {
//        return DateUtils.dateToString(new Date()) + AccountRemarkManagement.REPLACE_ACTIVITY.getRemark();
//    }
//
//    /**
//     * 手动下发赠金
//     *
//     * @param account 交易账号
//     * @param userId  下发人
//     */
//    public abstract void manualSendCreditAmount(ActivityAccount account, Integer userId);
//
//
//    /**
//     * 下发赠金
//     *
//     * @param account 交易账号
//     * @param userId  下发操作人
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void sendActivityAccountCreditAmount(ActivityAccount account, Integer userId) {
//        LockExecutor.execute(LockExecutor.getLockKey(LockKeyManagement.SEND_CREDIT.getKey() +
//            account.getTradingAccount().toString()), () -> {
//            //下发赠金
//            this.sendCredit(account, userId);
//        });
//    }
//
//    /**
//     * 下发赠金
//     *
//     * @param activityAccount 交易账号
//     * @param userId          下发操作人
//     */
//    private void sendCredit(ActivityAccount activityAccount, Integer userId) {
//        log.info("MarketingHandler #sendCredit #account:{} #userId:{}", activityAccount.getTradingAccount(), userId);
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//        RepositoryFactory repositoryFactory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = repositoryFactory.createActivityAccountRepository();
//
//        ActivityAccount account = activityAccountRepository.loadAccountInfo(activityAccount.getMerchantsId(), activityAccount.getId());
//        if (account == null || account.getWaitingCredit().compareTo(BigDecimal.ZERO) < 1 || !account.getAccountStatus()) {
//            log.info("账号:{} #下发条件不满足", activityAccount.getTradingAccount());
//            return;
////            throw new BusinessException(BusinessMsg.MAAE0103);
//        }
//        //获取MT4备注信息
//        String comment = CommonUtil.getMt4Comment(account, Mt4Comment.SEND_CREDIT);
//        BigDecimal alreadyCredit = account.getWaitingCredit().add(account.getAlreadyCredit());
//        account.setAlreadyCredit(alreadyCredit);
//        CreditUpdateAccountReq req = new CreditUpdateAccountReq();
//        BigDecimal amt = account.getWaitingCredit();
//        req.setAmt(amt);
//        req.setServer(account.getServerId());
//        req.setClientLogin(account.getTradingAccount());
//        req.setDays(account.getDays());
//        req.setComment(comment);
//        account.setWaitingCredit(BigDecimal.ZERO);
//        CreditsUpdateResp result = commonDomainService.updateCredit(account.getMerchantsId(), req);
//        if (null != result) {
//            //修改赠金数量
//            account.setOptTime(DateUtils.getCurDate());
//            account.setOptUser(userId);
//            activityAccountRepository.modifyAccountCreditAmount(account);
//            if (account.getCanBalance()) {
//                log.info("当前交易账号:{}满足交易手数要求，下发赠金时修改可提现金额", account.getTradingAccount());
//                if (!account.getCanBalanceUpdate()) {
//                    account.setCanBalanceAmount(account.getAlreadyCredit());
//                    activityAccountRepository.updateCanBalanceAmount(account);
//                }
//                log.info("账号:{}可提现赠金修改完毕", account.getTradingAccount());
//            }
//        }
//
//        ActivityDetailDto detailDto = new ActivityDetailDto(account.getActivityId());
//        SubType subType = detailDto.getActivityTemplate().getSubType();
//        if (subType == SubType.OPEN_PHYSICAL) {
//            //记录下发流水
//            UserActivityBonusDetail.saveCreditRecords(account, amt, userId, PayType.OPEN);
//        }
//        //保存金额修改记录
//        AmountDetail.amountChangeRecord(account, BusinessType.MANUAL_SEND, userId.toString());
//        log.info("MarketingHandler #sendCredit #end");
//    }
//
//    /**
//     * 手动释放赠金
//     *
//     * @param account 交易账号信息
//     * @param userId  用户ID
//     */
//    public abstract void manualIntoCreditAmount(ActivityAccount account, Integer userId);
//
//    /**
//     * 释放赠金
//     *
//     * @param account 交易账号
//     * @param userId  下发操作人
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void intoActivityAccountCreditAmount(ActivityAccount account, Integer userId) {
//        LockExecutor.execute(LockExecutor.getLockKey(LockKeyManagement.INTO_CREDIT.getKey() +
//            account.getTradingAccount().toString()), () -> {
//            //释放赠金
//            String recyclingRemark = "手动释放赠金后回收赠金";
//            this.intoCredit(account, userId, recyclingRemark);
//        });
//    }
//
//    /**
//     * 释放赠金
//     *
//     * @param account         交易账号
//     * @param userId          下发操作人
//     * @param recyclingRemark 回收流水备注
//     */
//    private void intoCredit(ActivityAccount account, Integer userId, String recyclingRemark) {
//        log.info("MarketingHandler #intoCredit #start #account:{} #userId:{} #recyclingRemark:{}",
//            account.getTradingAccount(), userId, recyclingRemark);
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//        RepositoryFactory repositoryFactory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = repositoryFactory.createActivityAccountRepository();
//
//        ActivityAccount activityAccount = activityAccountRepository.loadAccount(account.getMerchantsId(), account.getTradingAccount());
//        if (null == activityAccount || !activityAccount.getAccountStatus() || !activityAccount.getCanBalance() || !activityAccount.checkTradeVolume()) {
//            log.info("账号:{} #释放条件不满足", account.getTradingAccount());
//            return;
////            throw new BusinessException(BusinessMsg.MAAE0104);
//        }
//
//        //获取MT4备注信息
//        String comment = CommonUtil.getMt4Comment(activityAccount, Mt4Comment.SEND_BALANCE);
//        BalanceUpdateAccountReq updateAccountReq = new BalanceUpdateAccountReq();
//        BigDecimal amt = activityAccount.getCanBalanceAmount();
//        updateAccountReq.setServer(activityAccount.getServerId());
//        updateAccountReq.setAmt(amt);
//        updateAccountReq.setComment(comment);
//        updateAccountReq.setClientLogin(activityAccount.getTradingAccount());
//        //释放赠金
//        List<BalanceUpdateResp> balanceUpdateResp = commonDomainService.updateBalance(activityAccount.getMerchantsId(), updateAccountReq);
//        if (!CollectionUtils.isEmpty(balanceUpdateResp)) {
//            //赠金释放后将账号的credit归0
//            CreditsUpdateResp resp = commonDomainService.updateCredit(activityAccount.getMerchantsId(), new MarketingCreditUpdateReq().
//                convertReq(activityAccount, MathUtil.convertNum(activityAccount.getAlreadyCredit()),
//                    CommonUtil.getMt4Comment(activityAccount, Mt4Comment.RECYCLING_CREDIT)));
//
//            if (null != resp) {
//                activityAccount.setRemark("extract on " + DateUtils.dateToString(new Date()));
//                activityAccount.setCanBalanceAmount(BigDecimal.ZERO);
//                activityAccount.setAccountStatus(false);
//                activityAccount.setOptUser(userId);
//                activityAccount.setOptTime(new Date());
//                //修改可提取赠金金额和状态
//                activityAccountRepository.modifyAccountCreditAmountInto(activityAccount);
//                //记录释放明细
//                UserActivityBonusDetail.saveCreditRecords(activityAccount, amt, userId, PayType.INTO);
//                RecyclingDetail.saveRecyclingRecord(activityAccount, OperationType.MANUAL,
//                    PayType.MANUAL_RECYCLING, resp.getUpdateAmount(),
//                    BigDecimal.ZERO, recyclingRemark, userId, new Date());
//            }
//        }
//        //保存金额修改记录
//        AmountDetail.amountChangeRecord(activityAccount, BusinessType.MANUAL_INTO, userId.toString());
//        log.info("MarketingHandler #intoCredit #ending #account:{} ", account.getTradingAccount());
//    }
//
//    /**
//     * 手动回收赠金
//     *
//     * @param account 交易账号信息
//     * @param userId  手动回收人ID
//     */
//    public abstract void manualRecyclingCredit(ActivityAccount account, Integer userId);
//
//    /**
//     * 手动回收赠金
//     *
//     * @param account 交易账号信息
//     * @param userId  当前操作人
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void recyclingActivityAccountCreditAmount(ActivityAccount account, Integer userId) {
//        LockExecutor.execute(LockExecutor.getLockKey(LockKeyManagement.MANUAL_RECYCLING_CREDIT.getKey() +
//            account.getTradingAccount().toString()), () -> recyclingCredit(account, userId));
//    }
//
//    /**
//     * 手动回收赠金操作逻辑
//     *
//     * @param activityAccount 交易账号信息
//     * @param userId          操作人
//     */
//    private void recyclingCredit(ActivityAccount activityAccount, Integer userId) {
//
//        log.info("MarketingHandler #recyclingCredit #account:{} #userId:{}",
//            activityAccount.getTradingAccount(), userId);
//
//        DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//
//        ActivityAccount account = activityAccountRepository.
//            loadAccountInfoByAccount(activityAccount.getTradingAccount(), activityAccount.getMerchantsId());
//        log.info("account:{}", com.aliyun.openservices.shade.com.alibaba.fastjson.JSON.toJSONString(account));
//
//        if (null == account || !account.getAccountStatus()) {
//            log.info("账号:{} #回收条件不满足", activityAccount.getTradingAccount());
//            return;
////            throw new BusinessException(BusinessMsg.MAAE0105);
//        }
//
//        //获取MT4备注信息
//        String comment = CommonUtil.getMt4Comment(account, Mt4Comment.RECYCLING_CREDIT);
//        CreditUpdateAccountReq req = new CreditUpdateAccountReq();
//        BigDecimal alreadyCredit = account.getAlreadyCredit();
//        BigDecimal recyclingCredit = MathUtil.convertNum(alreadyCredit);
//        req.setAmt(recyclingCredit);
//        req.setServer(account.getServerId());
//        req.setClientLogin(account.getTradingAccount());
//        req.setDays(account.getDays());
//        req.setComment(comment);
//        CreditsUpdateResp creditsUpdateResp = commonDomainService.updateCredit(account.getMerchantsId(), req);
//        if (null != creditsUpdateResp) {
//            account.setAccountStatus(false);
//            account.setOptUser(userId);
//            account.setOptTime(new Date());
//            String accountRemark = DateUtils.dateToString(new Date()) + " " + AccountRemarkManagement.MANDATORY_WITHDRAWAL.getRemark();
//            account.setRemark(StringUtil.isEmpty(account.getRemark()) ? accountRemark : account.getRemark() + " " + accountRemark);
//            //增加赠金回收流水
//            String recyclingRemark = "手动回收";
//            RecyclingDetail.saveRecyclingRecord(activityAccount, OperationType.MANUAL, PayType.MANUAL_RECYCLING,
//                creditsUpdateResp.getUpdateAmount(), BigDecimal.ZERO, recyclingRemark, userId, new Date());
//        }
//        activityAccountRepository.editAccountStatus(account);
//        //记录回收记录
//        UserActivityBonusDetail.saveCreditRecords(account, MathUtil.convertNum(creditsUpdateResp.getUpdateAmount()), userId, PayType.MANUAL_RECYCLING);
//        //给上级销售发送邮件
//        this.sendMailToSales(account);
//        //保存金额修改记录
//        AmountDetail.amountChangeRecord(account, BusinessType.MANUAL_RECYCLING, userId.toString());
//        log.info("MarketingHandler #recyclingCredit #end");
//    }
//
//    /**
//     * 活动关闭后处理账号逻辑
//     *
//     * @param account          交易账号
//     * @param recyclingComment 回收备注
//     * @param type             操作类型
//     */
//    public abstract void closeActivity(ActivityAccount account, String recyclingComment, PayType type);
//
//    /**
//     * 活动关闭后处理账号逻辑
//     *
//     * @param account          交易账号
//     * @param recyclingComment 回收备注
//     * @param type             操作类型
//     */
//    public void closeActivityToDealWithAccount(ActivityAccount account, String recyclingComment, PayType type) {
//        //定时关闭活动处理账号逻辑
//        log.info("MarketingHandler #closeActivityToDealWithAccount #start #account:{}", account.getTradingAccount());
//        log.info("开始回收赠金，回收账号为:{},该账号参加的活动ID为：{}",
//            JSON.toJSONString(account.getTradingAccount()), JSON.toJSONString(account.getActivityId()));
//        LockExecutor.execute(LockKeyManagement.UPDATE_CREDIT.getKey() + account.getMerchantsId() +
//            account.getActivityId() + account.getTradingAccount(), () -> {
//            //处理活动关闭逻辑
//            toDealWithCloseActivity(account, recyclingComment, type);
//        });
//        log.info("MarketingHandler #closeActivityToDealWithAccount #end #account:{}", account.getTradingAccount());
//    }
//
//    /**
//     * 处理活动关闭逻辑
//     *
//     * @param account          交易账号信息
//     * @param releaseType      释放类型
//     * @param recyclingComment 回收备注
//     * @param type             操作类型
//     */
//    private void toDealWithTradeTimeRecyclingCredit(ActivityAccount account, Integer releaseType, String recyclingComment, PayType type) {
//
//        log.info("MarketingHandler #toDealWithTradeTimeRecyclingCredit #account:{} #recyclingComment:{} #type:{}",
//            account.getTradingAccount(), recyclingComment, type.getDesc());
//
//        RepositoryFactory repositoryFactory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = repositoryFactory.createActivityAccountRepository();
//
//        ActivityAccount activityAccount =
//            activityAccountRepository.loadAccountInfo(account.getMerchantsId(), account.getId());
//
//        //校验账号状态
//        if (null != activityAccount && activityAccount.getAccountStatus()) {
//
//            //不释放
//            if (releaseType == 0) {
//                //不释放的活动在交易有效期结束后执行赠金回收操作
//                this.tradeTimeRecyclingCredit(activityAccount, recyclingComment, type);
//                return;
//            }
//
//            //账号满足释放条件
//            if (activityAccount.getCanBalance()) {
//                if (releaseType == 1) {
//                    //自动释放
//                    this.tradeTimeAutomaticRelease(activityAccount);
//                } else if (releaseType == 2) {
//                    //手动释放
//                    //手动释放活动在交易有效期过期后不处理账号
//                    return;
//                }
//            } else {
//                //账号不满足释放条件
//                //回收赠金
//                this.tradeTimeRecyclingCredit(activityAccount, recyclingComment, type);
//            }
//        }
//        log.info("MarketingHandler #toDealWithTradeTimeRecyclingCredit #end");
//    }
//
//    /**
//     * 交易有效期到期自动释放逻辑
//     *
//     * @param activityAccount 交易账号
//     */
//    private void tradeTimeAutomaticRelease(ActivityAccount activityAccount) {
//        log.info("MarketingHandler #toDealWithTradeTimeRecyclingCredit #start #account:{}", activityAccount.getTradingAccount());
//        String recyclingRemark = "检查交易有效期回收";
//        //处理释放逻辑
//        this.intoCredit(activityAccount, ActivityConstant.SYSTEM_ID, recyclingRemark);
//        log.info("MarketingHandler #toDealWithTradeTimeRecyclingCredit #ending #account:{}", activityAccount.getTradingAccount());
//    }
//
//    /**
//     * 回收赠金
//     *
//     * @param activityAccount  交易账号信息
//     * @param recyclingComment 回收备注
//     * @param type             回收类型
//     */
//    private void tradeTimeRecyclingCredit(ActivityAccount activityAccount, String recyclingComment, PayType type) {
//        log.info("MarketingHandler #TradeTimeRecyclingCredit #start #account:{}", activityAccount.getTradingAccount());
//        DomainServiceFactory domainFactory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = domainFactory.createCommonDomainService();
//        RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//        BigDecimal amt = MathUtil.convertNum(activityAccount.getAlreadyCredit());
//        //调用CRM开始扣减赠金
//        CreditsUpdateResp result = commonDomainService.updateCredit(activityAccount.getMerchantsId(), new MarketingCreditUpdateReq().
//            convertReq(activityAccount, amt, CommonUtil.getMt4Comment(activityAccount, Mt4Comment.RECYCLING_CREDIT)));
//        log.info("账号：{} 赠金回收完毕", activityAccount.getTradingAccount());
//        if (null != result) {
//            activityAccount.setOptUser(ActivityConstant.SYSTEM_ID);
//            activityAccount.setOptTime(DateUtils.getCurDate());
//            activityAccount.setAccountStatus(false);
//            activityAccount.setRemark(activityAccount.getRemark() == null ? recyclingComment : activityAccount.getRemark() + " " + recyclingComment);
//            activityAccountRepository.recyclingCreditByActivityEnding(activityAccount);
//
//            //记录赠金明细
//            UserActivityBonusDetail.saveCreditRecords(activityAccount, MathUtil.convertNum(result.getUpdateAmount()), ActivityConstant.SYSTEM_ID, PayType.MANUAL_RECYCLING);
//
//            //记录回收流水
//            RecyclingDetail.saveRecyclingRecord(activityAccount, OperationType.SYSTEM, type, result.getUpdateAmount(), BigDecimal.ZERO,
//                recyclingComment, ActivityConstant.SYSTEM_ID, new Date());
//            log.info("账号:{} 信息更新完毕", activityAccount.getTradingAccount());
//            HashMap<String, String> param = new HashMap<>(3);
//            param.put("recycling of reason", recyclingComment);
//            param.put("recycling credit", result.getUpdateAmount().toString());
//            param.put("recycling date", DateUtils.dateToString(new Date()));
//            log.info("record account:{} amount change record start", activityAccount.getTradingAccount());
//            AmountDetail.amountChangeRecord(activityAccount, BusinessType.AUTOMATIC_RECYCLING, JSON.toJSONString(param));
//            log.info("record account:{} amount change record successful", activityAccount.getTradingAccount());
//        }
//        log.info("MarketingHandler #TradeTimeRecyclingCredit #ending #account:{}", activityAccount.getTradingAccount());
//    }
//
//    /**
//     * 处理活动关闭逻辑
//     *
//     * @param account          交易账号信息
//     * @param recyclingComment 回收备注
//     * @param type             操作类型
//     */
//    private void toDealWithCloseActivity(ActivityAccount account, String recyclingComment, PayType type) {
//
//        log.info("MarketingHandler #toDealWithCloseActivity #account:{} #recyclingComment:{} #type:{}",
//            account.getTradingAccount(), recyclingComment, type.getDesc());
//
//        RepositoryFactory repositoryFactory = RepositoryBuilder.instance().getFactory();
//        ActivityAccountRepository activityAccountRepository = repositoryFactory.createActivityAccountRepository();
//
//        ActivityAccount activityAccount =
//            activityAccountRepository.loadAccountInfo(account.getMerchantsId(), account.getId());
//        if (null != activityAccount && activityAccount.getAccountStatus()) {
//            tradeTimeRecyclingCredit(account, recyclingComment, type);
//        }
//        log.info("MarketingHandler #toDealWithCloseActivity #end");
//    }
//
//    /**
//     * 检查交易手数
//     *
//     * @param account 交易账号信息
//     */
//    public abstract void checkTradeVolume(ActivityAccount account);
//
//    /**
//     * 检查交易有效期
//     *
//     * @param account           交易账号信息
//     * @param activityDetailDto 活动规则通用实体
//     * @param remark            账号信息备注
//     */
//    public abstract void checkTradeTime(ActivityAccount account, ActivityDetailDto activityDetailDto, String remark);
//
//    /**
//     * 处理交易有效期过期账号
//     *
//     * @param account          交易账号信息
//     * @param releaseType      释放类型
//     * @param recyclingComment 回收备注信息
//     * @param payType          回收类型
//     */
//    public void checkTradeTimeRecyclingCredit(ActivityAccount account, Integer releaseType, String recyclingComment, PayType payType) {
//        //处理交易有效期过期账号
//        log.info("MarketingHandler #checkTradeTimeRecyclingCredit #start #account:{}", account.getTradingAccount());
//        log.info("开始回收赠金，回收账号为:{},该账号参加的活动ID为：{}",
//            JSON.toJSONString(account.getTradingAccount()), JSON.toJSONString(account.getActivityId()));
//        LockExecutor.execute(LockKeyManagement.UPDATE_CREDIT.getKey() + account.getMerchantsId() +
//            account.getActivityId() + account.getTradingAccount(), () -> {
//            //处理交易有效期过期逻辑
//            this.toDealWithTradeTimeRecyclingCredit(account, releaseType, recyclingComment, payType);
//        });
//        log.info("MarketingHandler #checkTradeTimeRecyclingCredit #end #account:{}", account.getTradingAccount());
//    }
//
//    /**
//     * 检查交易账号净值
//     *
//     * @param account   交易账号信息
//     * @param detailDto 活动规则
//     */
//    public abstract void checkEquity(ActivityAccount account, ActivityDetailDto detailDto);
//
//    /**
//     * 处理检查净值逻辑公用方法
//     *
//     * @param account          交易账号信息
//     * @param lossProportion   不可抗亏损比例
//     * @param amountProportion 赠金金额比例
//     * @param creditLoss       赠金可抗亏损设置
//     */
//    public void toDealWithCheckEquity(ActivityAccount account, Integer lossProportion,
//                                      Integer amountProportion, Integer creditLoss) {
//
//        log.info("MarketingHandler #toDealWithCheckEquity #start #time{}", DateUtils.dateToString(DateUtils.getCurDate()));
//
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = factory.createCommonDomainService();
//        CacheEmail cacheEmail = CacheBuilder.instance().getFactory().createCacheEmail();
////        AccNum01Users accNum01Users = commonDomainService.loadAccNum01User(account.getServerId(), account.getMerchantsId(), account.getTradingAccount());
//        //2022-10-26 Mt4内存飙升，变更为查询同步库
//        AccNum01UserResp accNum01Users = commonDomainService.queryAccountInfo(account.getMerchantsId(), account.getServerId(), account.getTradingAccount());
//
//        log.info("调用CRM接口获取的交易账号信息：{}", JSON.toJSONString(accNum01Users));
//        if (null != accNum01Users && MathUtil.isGreaterThan(BigDecimal.valueOf(accNum01Users.getCredit()), BigDecimal.ZERO)) {
//            BigDecimal credit = BigDecimal.valueOf(accNum01Users.getCredit());
//            BigDecimal equity = BigDecimal.valueOf(accNum01Users.getEquity());
//            BigDecimal balance = BigDecimal.valueOf(accNum01Users.getBalance());
//
//            //如果同步库查询是0，就从mt4实时接口查询
//            if (MathUtil.equal(equity, BigDecimal.ZERO)) {
//                Mt4AccountInfo mt4AccountInfo = commonDomainService.loadMt4UserInfo(account.getMerchantsId(), account.getServerId(), account.getTradingAccount());
//                //实时接口查询Equity也是0
//                if (MathUtil.equal(mt4AccountInfo.getEquity(), BigDecimal.ZERO)) {
//                    log.info("当前账号:{} 的净值为0 #记录异常信息开始", account.getTradingAccount());
//                    ActivityRepository activityRepository = RepositoryBuilder.instance().getFactory().createActivityRepository();
//                    Activity activity = activityRepository.loadActivityByActivityId(account.getMerchantsId(), account.getActivityId());
//                    AccountEquityAbnormal.insertRecord(activity, account, equity, credit, balance, ActivityConstant.SYSTEM_ID, DateUtils.getCurDate());
//                    log.info("当前账号:{} #报名ID:{} #记录异常信息结束", account.getTradingAccount(), account.getId());
//                    return;
//                }
//                //同步库数据查询有问题，改为使用实时接口获得数据
//                equity = mt4AccountInfo.getEquity();
//                credit = mt4AccountInfo.getCredit();
//            }
//
//
//            log.info("当前账号:{},净值为:{}", account.getTradingAccount(), equity);
//            String email = cacheEmail.getEmail("marketing_email_" + account.getEmail() + "_" + account.getTradingAccount());
//            log.info("缓存中获取到邮箱:{},账号：{}", email, account.getTradingAccount());
//
//            BigDecimal mailRuleEquity = credit.multiply(BigDecimal.valueOf(1.1));
//
//            // 如果触发点净值额度小于等于当前净值，则触发邮件
//            // 如果缓存中没有邮箱，则证明是今天第一次，正常发送，发送后把邮箱缓存至Redis
//            if (equity.compareTo(mailRuleEquity) < 1 && Boolean.TRUE.equals(StringUtil.isEmpty(email))) {
//                log.info("账号:{}，触发净值检查警告邮件", account.getTradingAccount());
//                publicService.sendCheckEquityEmail(account, amountProportion);
//            }
//
//            //类型为可抗亏损
//            if (creditLoss == CreditLossType.LOSS.getId()) {
//                //净值小于等于0并且账号累计入金时间已过期
//                if (account.getCumulativeDepositTime() != null) {
//                    if (equity.compareTo(BigDecimal.ZERO) < 1 &&
//                        account.getCumulativeDepositTime().getTime() < System.currentTimeMillis()) {
//                        log.info("开始扣减可抗亏损赠金，并修改活动内账号状态和赠金可提取金额");
//                        //当净值小于等于不可抗亏损金额时，赠金收回，账号禁用
//                        this.checkEquityRecyclingCredit(account);
//                    }
//                }
//            } else {
//                //赠金不可抗亏损逻辑
//                BigDecimal lossProportionProportion = new BigDecimal(lossProportion);
//                //获取不可抗亏损比例
//                BigDecimal divide = lossProportionProportion.divide(new BigDecimal(100));
//                //不可抗亏损金额
//                BigDecimal lossProportionAmount = credit.multiply(divide);
//                log.info("当前账号:{},不可抗亏损的赠金额度为:{}", account.getTradingAccount(), lossProportion);
//                if (equity.compareTo(lossProportionAmount) < 1) {
//                    log.info("开始扣减不可抗亏损赠金，并修改活动内账号状态和赠金可提取金额");
//                    //当净值小于等于不可抗亏损金额时，赠金收回，账号禁用
//                    this.checkEquityRecyclingCredit(account);
//                }
//            }
//        }
//
//        log.info("MarketingHandler #toDealWithCheckEquity #end #time{}", DateUtils.dateToString(DateUtils.getCurDate()));
//    }
//
//    /**
//     * 检查净值违规回收赠金
//     *
//     * @param account 账号信息
//     */
//    private void checkEquityRecyclingCredit(ActivityAccount account) {
//
//        log.info("MarketingHandler #checkEquityRecyclingCredit #account:{} #start #time{}",
//            account.getTradingAccount(), DateUtils.dateToString(DateUtils.getCurDate()));
//
//        LockExecutor.execute(LockKeyManagement.UPDATE_CREDIT.getKey() + account.getMerchantsId() +
//            account.getActivityId() + account.getTradingAccount(), () -> {
//            RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//            ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//            DomainServiceFactory domainServiceFactory = DomainServiceBuilder.instance().getFactory();
//            CommonDomainService commonDomainService = domainServiceFactory.createCommonDomainService();
//            ActivityAccount activityAccount =
//                activityAccountRepository.loadAccountInfo(account.getMerchantsId(), account.getId());
//
//            if (null != activityAccount && activityAccount.getAccountStatus()) {
//                //需要扣减的金额
//                BigDecimal amount = MathUtil.convertNum(account.getAlreadyCredit());
//                activityAccount.setWaitingCredit(amount);
//                //开始调用CRM接口扣减赠金
//                CreditsUpdateResp result = commonDomainService.
//                    updateCredit(activityAccount.getMerchantsId(), new MarketingCreditUpdateReq().
//                        convertReq(activityAccount, amount, CommonUtil.getMt4Comment(activityAccount, Mt4Comment.RECYCLING_CREDIT)));
//
//                if (null != result) {
//                    if (Boolean.TRUE.equals(activityAccount.getCanBalance())) {
//                        activityAccount.setCanBalanceAmount(BigDecimal.ZERO);
//                    }
//                    String recyclingRemark = "检查净值违规回收";
//                    activityAccount.setAccountStatus(false);
//                    activityAccount.setOptTime(new Date());
//                    activityAccount.setRemark(DateUtils.dateToString(new Date()) + AccountRemarkManagement.CHECK_EQUITY.getRemark());
//
//                    //记录赠金明细
//                    UserActivityBonusDetail.saveCreditRecords(activityAccount, MathUtil.convertNum(result.getUpdateAmount()),
//                        ActivityConstant.SYSTEM_ID, PayType.MANUAL_RECYCLING);
//
//                    //记录回收流水
//                    RecyclingDetail.saveRecyclingRecord(activityAccount, OperationType.SYSTEM, PayType.EQUITY, result.getUpdateAmount(),
//                        BigDecimal.ZERO, recyclingRemark, ActivityConstant.SYSTEM_ID, new Date());
//                    activityAccountRepository.editAccountStatusByLossProportion(activityAccount);
//                    HashMap<String, String> param = new HashMap<>(4);
//                    param.put("recycling account", activityAccount.getTradingAccount().toString());
//                    param.put("recycling amount", result.getUpdateAmount().toString());
//                    param.put("recycling date", DateUtils.dateToString(new Date()));
//                    param.put("recycling reason", "check equity");
//                    log.info("record account:{} amount change record start", activityAccount.getTradingAccount());
//                    AmountDetail.amountChangeRecord(activityAccount, BusinessType.MANUAL_RECYCLING, JSON.toJSONString(param));
//                    log.info("record account:{} amount change record successful", activityAccount.getTradingAccount());
//                    log.info("赠金扣减完毕，账号状态修改完毕");
//                } else {
//                    log.info("调用CRM接口扣减赠金失败，账号不予修改");
//                }
//            }
//        });
//        log.info("MarketingHandler #checkEquityRecyclingCredit #account:{} #end #time{}",
//            account.getTradingAccount(), DateUtils.dateToString(DateUtils.getCurDate()));
//    }
//
//    /**
//     * 处理出金订单
//     *
//     * @param account          交易账号信息
//     * @param crmWithdrawalReq 出金订单请求信息
//     * @param detailDto        规则公用实体
//     * @return 处理结果
//     */
//    public abstract Boolean withdrawal(ActivityAccount account, CrmWithdrawalReq crmWithdrawalReq, ActivityDetailDto detailDto);
//
//    /**
//     * 处理转账订单
//     *
//     * @param account        交易账号信息
//     * @param crmTransferReq 转账订单请求信息
//     * @param detailDto      规则公用实体
//     * @return 处理结果
//     */
//    public abstract Boolean transfer(ActivityAccount account, CrmTransferReq crmTransferReq, ActivityDetailDto detailDto);
//
//    /**
//     * 获取出金回收备注
//     *
//     * @return 备注信息
//     */
//    public String getWithdrawalRemark() {
//        return DateUtils.dateToString(new Date()) + " " + AccountRemarkManagement.WITHDRAW_RECYCLING.getRemark();
//    }
//
//    /**
//     * 获取账号转账回收备注
//     *
//     * @return 备注信息
//     */
//    public String getTransferRemark() {
//        return DateUtils.dateToString(new Date()) + AccountRemarkManagement.TRANSFER_RECYCLING.getRemark();
//    }
//
//    /**
//     * 回收赠金备注
//     *
//     * @param account 交易账号信息
//     * @param comment 备注信息
//     * @return 备注
//     */
//    public String getRemark(ActivityAccount account, String comment) {
//        return account.getRemark() == null ? comment : account.getRemark() + " " + comment;
//    }
//
//    /**
//     * 交易账号违反活动规则后回收赠金
//     *
//     * @param accountInfo 交易账号信息
//     * @param comment     回收备注
//     * @param payType     操作类型
//     */
//    public BigDecimal compulsoryRecyclingCredit(ActivityAccount accountInfo, String comment, PayType payType) {
//        log.info("MarketingHandler #compulsoryRecyclingCredit #account:{} #payType:{} #start",
//            accountInfo.getTradingAccount(), payType.getDesc());
//
//        BigDecimal result = BigDecimal.ZERO;
//
//        //账号已下发赠金不是null  && 账号已下发赠金大于0
//        if (null != accountInfo.getOperAmount() && MathUtil.isGreaterThan(accountInfo.getOperAmount(), BigDecimal.ZERO)) {
//            BigDecimal amt = MathUtil.convertNum(accountInfo.getOperAmount());
//            //开始调用CRM接口扣减赠金
//            DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//            CommonDomainService commonDomainService = factory.createCommonDomainService();
//            CreditsUpdateResp resp = commonDomainService.updateCredit(accountInfo.getMerchantsId(), new MarketingCreditUpdateReq().
//                convertReq(accountInfo, amt, CommonUtil.getMt4Comment(accountInfo, Mt4Comment.RECYCLING_CREDIT)));
//            if (null != resp) {
//                //增加回收流水
//                RecyclingDetail.saveRecyclingRecord(accountInfo, OperationType.SYSTEM, payType, resp.getUpdateAmount(), BigDecimal.ZERO,
//                    comment, ActivityConstant.SYSTEM_ID, new Date());
//                log.info("当前交易账号:{},赠金已回收", accountInfo.getTradingAccount());
//                result = resp.getUpdateAmount();
//            }
//        }
//        log.info("MarketingHandler #compulsoryRecyclingCredit #account:{} #payType:{} #ending",
//            accountInfo.getTradingAccount(), payType.getDesc());
//        return result;
//    }
//
//    /**
//     * 处理交易订单
//     *
//     * @param accountInfo 交易账号信息
//     * @param detailDto   活动规则通用实体
//     * @param order       交易订单
//     * @param symbolGroup 查询Symbol所得的品种组信息
//     */
//    public abstract void dealWithTradeOrder(ActivityAccount accountInfo, ActivityDetailDto detailDto,
//                                            TradeOrderReq order, VarietyGroupConf symbolGroup);
//
//    /**
//     * 校验累计手数条件是否满足（true：正常累计，false：不累计）
//     *
//     * @param account 交易账号信息
//     * @param req     订单信息
//     * @return 校验结果
//     */
//    public abstract Boolean checkCumulativeVolumeConditions(ActivityAccount account, TradeOrderReq req);
//
//    /**
//     * 校验交易组
//     *
//     * @param dto            规则实体
//     * @param serverId       server
//     * @param tradingGroupId 交易组
//     * @return 校验结果
//     */
//    public abstract Boolean checkTradingGroup(ActivityDetailDto dto, Integer serverId, String tradingGroupId);
//
//    /**
//     * 校验交易组公用逻辑
//     *
//     * @param screeningType  交易组筛选类型 （0：参与，1：排除）
//     * @param dtoList        活动配置的交易组
//     * @param serverId       用户所属Server
//     * @param tradingGroupId 用户所属交易组
//     * @return 校验结果
//     */
//    public Boolean checkTradingGroup(Integer screeningType, List<TradingServerDto> dtoList, Integer serverId, String tradingGroupId) {
//
//        log.info("MarketingHandler #checkTradingGroup #start #time:{} #screeningType:{} #server:{} #tradingGroup:{}",
//            DateUtils.dateToString(new Date()), screeningType, serverId, tradingGroupId);
//        if (CollectionUtils.isEmpty(dtoList)) {
//            return false;
//        }
//
//        Map<Integer, List<TradingServerDto>> map = dtoList.stream().collect(Collectors.groupingBy(TradingServerDto::getServerId));
//
//        if (CollectionUtils.isEmpty(map)) {
//            return false;
//        }
//
//        //参与类型校验
//        if (screeningType == 0) {
//            return this.checkParticipateTradingGroup(map, serverId, tradingGroupId);
//        }
//
//        //校验排除类型
//        return this.checkAvoidTradingGroup(map, serverId, tradingGroupId);
//    }
//
//    /**
//     * 校验排除类型交易组
//     *
//     * @param map            规则配置交易组信息
//     * @param serverId       客户所属server
//     * @param tradingGroupId 客户所属交易组
//     * @return 校验结果
//     */
//    protected Boolean checkAvoidTradingGroup(Map<Integer, List<TradingServerDto>> map, Integer serverId, String tradingGroupId) {
//        log.info("MarketingHandler #checkAvoidTradingGroup #start #time:{} #serverId:{} #tradingGroup:{}",
//            DateUtils.dateToString(new Date()), serverId, tradingGroupId);
//        for (Map.Entry<Integer, List<TradingServerDto>> entry : map.entrySet()) {
//
//            if (entry.getKey().equals(serverId)) {
//                List<TradingServerDto> value = entry.getValue();
//
//                for (TradingServerDto groupDto : value) {
//
//                    Boolean groupTag = groupDto.getAllTradingGroupTag();
//
//                    //tag未设置，则默认为不是全部
//                    if (null == groupTag) {
//                        groupTag = false;
//                    }
//
//                    //某个server下交易组勾选的是全部
//                    if (groupTag) {
//                        return false;
//                    }
//
//                    List<String> tradingGroup = groupDto.getTradingGroup();
//
//                    //排除类型 -> 未勾选交易组则证明所有交易组都可以参加
//                    if (CollectionUtils.isEmpty(tradingGroup)) {
//                        return true;
//                    }
//
//                    return !tradingGroup.contains(tradingGroupId);
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 校验参与类型交易组
//     *
//     * @param map            规则配置交易组信息
//     * @param serverId       客户所属server
//     * @param tradingGroupId 客户所属交易组
//     * @return 校验结果
//     */
//    protected Boolean checkParticipateTradingGroup(Map<Integer, List<TradingServerDto>> map, Integer serverId, String tradingGroupId) {
//        log.info("MarketingHandler #checkParticipateTradingGroup #start #time:{} #serverId:{} #tradingGroup:{}",
//            DateUtils.dateToString(new Date()), serverId, tradingGroupId);
//        for (Map.Entry<Integer, List<TradingServerDto>> entry : map.entrySet()) {
//
//            if (entry.getKey().equals(serverId)) {
//
//                List<TradingServerDto> value = entry.getValue();
//
//                for (TradingServerDto groupDto : value) {
//
//                    Boolean groupTag = groupDto.getAllTradingGroupTag();
//
//                    //tag未设置，则默认为不是全部
//                    if (null == groupTag) {
//                        groupTag = false;
//                    }
//
//                    //某个server下交易组勾选的是全部
//                    if (groupTag) {
//                        return true;
//                    }
//
//                    List<String> tradingGroup = groupDto.getTradingGroup();
//
//                    //交易组勾选参与，然后不勾选交易组，则默认所有交易组都不参与
//                    if (CollectionUtils.isEmpty(tradingGroup)) {
//                        return false;
//                    }
//
//                    if (tradingGroup.contains(tradingGroupId)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * MyAccount获取参与活动账号
//     *
//     * @param activityDetailDto 活动详情
//     * @param req               请求参数
//     * @return 参与活动账号
//     */
//    public abstract List<Integer> getAccount(ActivityDetailDto activityDetailDto, MatGetAccountReq req);
//
//    /**
//     * 特批报名
//     *
//     * @param merchantsId      商户号
//     * @param userId           用户ID
//     * @param req              请求参数
//     * @param dto              活动规则
//     * @param tradeAccountInfo 交易账号信息
//     * @param payType          操作类型
//     */
//    public abstract void specialApply(Integer merchantsId, Integer userId, SpecialApplyReq req, ActivityDetailDto dto,
//                                      TradeAccountInfo tradeAccountInfo, PayType payType);
//
//    /**
//     * 特批报名搜集数据
//     *
//     * @param merchantsId           商户号
//     * @param userId                特批报名操作人
//     * @param req                   请求参数
//     * @param dto                   活动规则
//     * @param tradeAccountInfo      交易账号信息
//     * @param payType               操作类型
//     * @param cumulativeDepositTime 入金有效期
//     * @param cumulativeVolumeTime  交易有效期
//     */
//    public void specialSignUp(Integer merchantsId, Integer userId, SpecialApplyReq req, ActivityDetailDto dto,
//                              TradeAccountInfo tradeAccountInfo, PayType payType, Date cumulativeDepositTime,
//                              Date cumulativeVolumeTime) {
//        //计算手数及获取交易订单
//        Map<BigDecimal, List<TradeOrder>> map = this.specialApplySupplementVolume(merchantsId, tradeAccountInfo.getServerId(),
//            tradeAccountInfo.getTradingAccount(), DateUtils.stringToDate(req.getFirstDepositTime()), dto);
//
//        BigDecimal volume = BigDecimal.ZERO;
//        List<TradeOrder> historyTradeOrders = new ArrayList<>();
//        for (Map.Entry<BigDecimal, List<TradeOrder>> entry : map.entrySet()) {
//            volume = entry.getKey();
//            historyTradeOrders = entry.getValue();
//        }
//        ActivityAccount applyAccount = new ActivityAccount();
//        applyAccount.addSpecialApply(merchantsId, req, volume, tradeAccountInfo,
//            cumulativeVolumeTime, cumulativeDepositTime, req.getActivityId(), userId);
//
//        //报名逻辑
//        this.applyLogic(req, dto, payType, userId, applyAccount, historyTradeOrders);
//    }
//
//    /**
//     * 特批报名补充历史交易手数
//     *
//     * @param merchantsId  商户号
//     * @param serverId     serverId
//     * @param tradeAccount 交易账号
//     * @return k:交易手数 v:交易订单
//     */
//    public Map<BigDecimal, List<TradeOrder>> specialApplySupplementVolume(Integer merchantsId, Integer serverId, Integer tradeAccount,
//                                                                          Date firstDepositTime, ActivityDetailDto dto) {
//        DomainServiceFactory factory = DomainServiceBuilder.instance().getFactory();
//        CommonDomainService commonDomainService = factory.createCommonDomainService();
//        HashMap<BigDecimal, List<TradeOrder>> map = new HashMap<>(1);
//        //查询账户MT4交易记录
//        List<AccNum01Trades> accNum01Trades = commonDomainService.tradesHistory(merchantsId, serverId, tradeAccount);
//        if (!CollectionUtils.isEmpty(accNum01Trades)) {
//            accNum01Trades = accNum01Trades.stream().filter(t -> t.getCloseTime().after(firstDepositTime)
//                && t.getCloseTime().before(new Date())).collect(Collectors.toList());
//        }
//        BigDecimal volume = BigDecimal.ZERO;
//        Date date = DateUtils.stringToDate("1971-01-01 00:00:00");
//        List<String> symbolList = new ArrayList<>();
//
//        List<Integer> speciesIds = new ArrayList<>();
//        //查询活动配置品种组
//        List<SpeciesGroupDto> speciesGroups = dto.getSpeciesGroupDtoList();
//        for (SpeciesGroupDto groupRelation : speciesGroups) {
//            speciesIds.add(groupRelation.getSpeciesId());
//            List<SpeciesServerDto> servers = groupRelation.getServers();
//            for (SpeciesServerDto server : servers) {
//                symbolList.addAll(server.getSymbolGroup());
//            }
//        }
//        List<String> symbols = commonDomainService.getSymbolByGroupId(merchantsId, speciesIds);
//        //查询交易组
//        log.info("当前交易账号历史交易集合为:{}", JSON.toJSONString(accNum01Trades));
//        List<TradeOrder> orderList = Lists.newArrayList();
//        if (!CollectionUtils.isEmpty(accNum01Trades)) {
//            for (AccNum01Trades mt4Trade : accNum01Trades) {
//                BigDecimal tradeVolume = null;
//                if (mt4Trade.getCmd().getId() <= 1 && mt4Trade.getCloseTime().getTime() > date.getTime()) {
//                    // 计算交易手数
//                    if (symbolList.contains(mt4Trade.getSymbol())) {
//                        //判断活动是交易品种是否是小手数
//                        tradeVolume = new BigDecimal(mt4Trade.getVolume()).divide(new BigDecimal(100)).
//                            divide(new BigDecimal(10));
//                    } else {
//                        if (symbols.contains(mt4Trade.getSymbol())) {
//                            tradeVolume = new BigDecimal(mt4Trade.getVolume()).divide(new BigDecimal(100));
//                        } else {
//                            log.info("当前订单号为:{},交易symbol为:{},不属于活动配置的品种组内，不与计算手数", mt4Trade.getTicket(), mt4Trade.getSymbol());
//                        }
//                    }
//                }
//                if (tradeVolume != null) {
//                    volume = volume.add(tradeVolume);
//                    TradeOrder to = new TradeOrder();
//                    to.setAccount(mt4Trade.getLogin());
//                    to.setTradeOrder(mt4Trade.getTicket().toString());
//                    to.setCurrency("USD");
//                    to.setGroup("特批补充数据");
//                    to.setCloseTime(mt4Trade.getCloseTime());
//                    to.setServer(serverId);
//                    to.setSymbol(mt4Trade.getSymbol());
//                    to.setMerchantsId(merchantsId);
//                    to.setVolume(mt4Trade.getVolume());
//                    to.setClosePrice(BigDecimal.valueOf(mt4Trade.getClosePrice()));
//                    to.setCmd(mt4Trade.getCmd().getId());
//                    to.setTradeVolume(tradeVolume.doubleValue());
//                    to.setPositionId(mt4Trade.getPositionId() == null ? mt4Trade.getTicket().toString() : mt4Trade.getPositionId().toString());
//                    orderList.add(to);
//                }
//            }
//            map.put(volume, orderList);
//        }
//        return map;
//    }
//
//    /**
//     * 特批报名报名逻辑
//     *
//     * @param req          请求参数
//     * @param dto          活动规则
//     * @param payType      操作类型
//     * @param userId       特批报名操作人
//     * @param applyAccount 报名账号信息
//     * @param orderList    交易订单
//     */
//    private void applyLogic(SpecialApplyReq req, ActivityDetailDto dto, PayType payType, Integer userId,
//                            ActivityAccount applyAccount, List<TradeOrder> orderList) {
//
//        //上锁
//        LockExecutor.execute(LockExecutor.getLockKey("_activity_specialApply_" + req.getActivityId() + "_" + req.getTradingAccount()), () -> {
//            RepositoryFactory factory = RepositoryBuilder.instance().getFactory();
//            ActivityAccountRepository activityAccountRepository = factory.createActivityAccountRepository();
//            ActivityAccount account = activityAccountRepository
//                .loadAccount(applyAccount.getMerchantsId(), applyAccount.getTradingAccount());
//            if (null != account) {
//                throw new BusinessException(BusinessMsg.MAAE0011);
//            }
//
//            //添加报名记录
//            activityAccountRepository.insert(applyAccount);
//
//            //保存金额修改记录
//            AmountDetail.amountChangeRecord(applyAccount, BusinessType.SPECIAL, JSON.toJSONString(req));
//
//            //保存赠金明细(记录金额待确认)
//            //仅记录明细用
//            applyAccount.setOperAmount(req.getCreditSum());
//            if (null != applyAccount.getCumulativeVolumeTime()) {
//                new UserActivityBonusDetail()
//                    .convertRecords(applyAccount, payType, req.getCumulativeDeposit(), userId)
//                    .addRecords();
//            }
//            //保存交易明细
//            saveTradeOrder(orderList, applyAccount);
//
//            //发送特批报名成功邮件
//            this.sendSpecialApplyEmail(applyAccount, dto.getActivity());
//        });
//    }
//
//    /**
//     * 发送特批报名成功邮件
//     *
//     * @param account  报名账号信息
//     * @param activity 活动信息
//     */
//    private void sendSpecialApplyEmail(ActivityAccount account, Activity activity) {
//        try {
//            //报名成功发送邮件通知
//            publicService.sendSpecialApplyEmail(account, activity);
//        } catch (Exception e) {
//            log.info("特批报名邮件通知异常：{}", JSON.toJSONString(e.getMessage()));
//        }
//    }
//
//    /**
//     * 保存交易订单
//     *
//     * @param orderList    交易订单
//     * @param applyAccount 申请账号
//     */
//    public void saveTradeOrder(List<TradeOrder> orderList, ActivityAccount applyAccount) {
//        TraderOrderRepository traderOrderRepository = RepositoryBuilder.instance()
//            .getFactory().createTraderOrderRepository();
//        orderList.forEach(t -> {
//            t.setAccountId(applyAccount.getId());
//            traderOrderRepository.insertOrder(t);
//        });
//    }
//
//    /**
//     * 处理审核通过入金请求
//     *
//     * @param activity        活动信息
//     * @param order           入金订单
//     * @param activityAccount 申请账号
//     */
//    public abstract void auditDeposit(Activity activity, PayDepositOrderReq order, ActivityAccount activityAccount);
//
//    /**
//     * 官网数据
//     *
//     * @param account   申请账号
//     * @param detailDto 活动详情
//     * @return 官网数据
//     */
//    public abstract BonusScheduleResp websiteData(ActivityAccount account, ActivityDetailDto detailDto);
//
//    /**
//     * 校验账号类型：主交易账号 or 同名交易账号
//     *
//     * @param activityDetailDto 活动规则
//     * @param accountSelect     账号类型
//     * @return 校验结果
//     */
//    public abstract Boolean checkAccountSelect(ActivityDetailDto activityDetailDto, Integer accountSelect);
//
//    /**
//     * 校验账号类型：主交易账号 or 同名交易账号
//     *
//     * @param rulesSelect   规则设置账号类型
//     * @param accountSelect 账号类型
//     * @return 校验结果
//     */
//    public Boolean checkAccountSelect(Integer rulesSelect, Integer accountSelect) {
//
//        if (rulesSelect == 0) {
//            //全部账号
//            return true;
//        }
//
//        if (rulesSelect == 1) {
//            //主交易账号
//            return accountSelect == 1;
//        }
//
//        if (rulesSelect == 2) {
//            //同名交易账号
//            return accountSelect == 2;
//        }
//        return false;
//    }

    /**
     * 转换活动规则为通用实体
     *
     * @param detailDto 通用实体参数
     * @return 公用实体
     */
    public abstract ActivityDetailDto convertActivityRules(ActivityDetailDto detailDto);
}
