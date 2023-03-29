package com.crointech.backstage.domain.entity.activity;

import com.alibaba.fastjson.JSON;
import com.crointech.backstage.domain.entity.activityaccount.ActivityAccount;
import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Jason Q
 * @create 2021-10-26 20:23
 * @Description 活动规则
 **/
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRule extends BaseEntity {

    private static final long serialVersionUID = -2173297032821137378L;
    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 报名方式
     */
    private Integer applyType;

    /**
     * 代理链条筛选类型
     */
    private Integer screeningType;

    /**
     * 赠金下发方式
     */
    private Integer issueType;

    /**
     * 账号类型
     */
    private Integer royaltyType;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 入金类型
     */
    private Integer depositType;

    /**
     * 入金累计时间
     */
    private Integer depositTime;

    /**
     * 时间类型
     */
    private Integer depositTimeType;

    /**
     * 活动总赠金
     */
    private BigDecimal amountSum;

    /**
     * 单个账号赠金上限
     */
    private BigDecimal accountAmountCeiling;

    /**
     * 活动最低入金金额
     */
    private BigDecimal minimumDepositAmount;

    /**
     * 赠金金额比例
     */
    private Integer amountProportion;

    /**
     * 完成交易手数比例
     */
    private Integer tradingNumberProportion;

    /**
     * 交易有效期
     */
    private Integer tradingValidityDay;

    /**
     * 出金或转账（转出）是否结束活动  -> 针对参加活动的用户，并不是结束活动
     */
    private Boolean endIdentity;

    /**
     * 出金类型1、出金或转账（转出） 2、按比例出金 3、盈利出金
     */
    private Integer withdrawalType;

    /**
     * 出金比例
     */
    private Integer withdrawalScale;

    /**
     * 出金模式1、常规按比例出金 2、低风险按比例出金
     */
    private Integer withdrawalModel;

    /**
     * 赠金可抗亏损设置
     */
    private Integer creditLoss;

    /**
     * 代理类型 1、全部人员 2、指定代理
     */
    private Integer agentType;

    /**
     * 不可抗亏损比例
     */
    private Integer lossProportion;

    /**
     * 释放类型（0：不释放，1：自动释放，2：人工释放）
     */
    private Integer releaseType;

    /**
     * 交易组类型（0：筛选交易组，1：全部交易组（全部交易组代表所有server下的所有交易组））
     */
    private Integer tradingGroupType;

    /**
     * 交易组筛选类型（0：参与，1：排除）
     */
    private Integer tradingGroupScreeningType;
//
//    /**
//     * 赠金回收计算
//     *
//     * @param tradeAmt      出金交易金额
//     * @param account       报名数据
//     * @param creditBalance 赠金余额
//     * @param balance       余额
//     * @Desc 分为常规和按比例
//     */
//    public boolean creditRecycl(BigDecimal tradeAmt, ActivityAccount account, BigDecimal creditBalance, BigDecimal balance) {
//        log.info("ActivityRule-creditRecycl-tradeAmt:{},-creditBalance:{}", tradeAmt, creditBalance);
//
//        //v2.4.6 如果是按比例出金，并且出金额度大于等于当前balance，则扣除已下发全部赠金，账号禁用
//        BigDecimal result = balance.setScale(2, RoundingMode.HALF_UP).subtract(tradeAmt);
//        log.info("账号:{}，出金前balance:{}，出金数额为:{},账号剩余:{}", account.getTradingAccount(), balance, tradeAmt, result);
//        if (WithdrawalType.SCALE.getId() == this.getWithdrawalType() && MathUtil.lessThanAndEqual(result, BigDecimal.ZERO)) {
//            account.setOperAmount(account.getAlreadyCredit());
//            account.setAccountStatus(Boolean.FALSE);
//            log.info("账号:{}，状态变更为:{}", account.getTradingAccount(), account.getAccountStatus());
//            return Boolean.FALSE;
//        }
//
//        if (WithdrawalType.NORMAL.getId() == this.getWithdrawalType()) {
//            normalWithdrawal(account, creditBalance);
//            return Boolean.TRUE;
//        } else if (WithdrawalType.SCALE.getId() == this.getWithdrawalType()) {
//            scaleWithdrawal(tradeAmt, account, creditBalance, balance);
//            return Boolean.FALSE;
//        } else if (WithdrawalType.PROFIT.getId() == this.getWithdrawalType()) {
//            profitWithdrawal(tradeAmt, account, creditBalance, balance);
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
//
//    /**
//     * 按比例赠金计算
//     *
//     * @param tradeAmt      出金交易金额
//     * @param account       报名数据
//     * @param creditBalance 赠金余额
//     * @param balance       余额
//     * @Desc 分为常规按比例和低分险按比例
//     */
//    private void profitWithdrawal(BigDecimal tradeAmt, ActivityAccount account, BigDecimal creditBalance, BigDecimal balance) {
//        //盈利出金公式（Balance-申请出金金额（转账转出金额）>=活动期间累计入金金额）
//        Integer profitFlag = balance.subtract(tradeAmt).compareTo(account.getCumulativeDeposit());
//        if (tradeAmt.compareTo(balance.setScale(2, RoundingMode.HALF_UP)) >= 0) {
//            account.setOperAmount(account.getAlreadyCredit());
//            account.setAccountStatus(Boolean.FALSE);
//            return;
//        }
//        if (profitFlag >= 0 && WithdrawalModel.NORMAL.getId() == this.getWithdrawalModel().intValue()) {
//            //盈利普通（正常出金，不影响赠金和活动资格进行）
//            return;
//        } else if (profitFlag < 0 && WithdrawalModel.NORMAL.getId() == this.getWithdrawalModel().intValue()) {
//            //盈利部分金额计算(公式：余额-累计入金)正数为盈利，负数为亏损
//            BigDecimal profitAmt = balance.subtract(account.getCumulativeDeposit());
//            //亏损时默认盈利为0
//            if (profitAmt.compareTo(BigDecimal.ZERO) < 0) {
//                profitAmt = BigDecimal.ZERO;
//            }
//            //超盈部分计算（公式：出金（转出）金额-盈利部分金额）
//            BigDecimal superProfitAmt = tradeAmt.subtract(profitAmt);
//            //盈利模板出金比例=入金比例
//            this.withdrawalScale = this.amountProportion;
//            //非盈利普通（盈利部分可以正常出金、超盈部分按照赠金比例进行赠金扣减）
//            normalModel(superProfitAmt, account, creditBalance);
//            //按比例出金需要扣减已发送赠金(已发送赠金减去需要扣减金额)
//            account.setAlreadyCredit(account.getAlreadyCredit().subtract(account.getOperAmount()));
//            account.setProfitAmt(profitAmt);
//            account.setSuperProfitAmt(superProfitAmt);
//        } else if (profitFlag >= 0 && WithdrawalModel.LOWER.getId() == this.getWithdrawalModel().intValue()) {
//            //盈利低分险（正常出金，不影响赠金和活动资格进行）
//            return;
//        } else if (profitFlag < 0 && WithdrawalModel.LOWER.getId() == this.getWithdrawalModel().intValue()) {
//            //非盈利低分险（取消活动资格并收回赠金）
//            account.setOperAmount(account.getAlreadyCredit());
//            account.setAccountStatus(Boolean.FALSE);
//            return;
//        } else {
//            log.info("activityRule-profitWithdrawal-未匹配到出金模式");
//        }
//    }
//
//    /**
//     * 按比例赠金计算
//     *
//     * @param tradeAmt      出金交易金额
//     * @param account       报名数据
//     * @param creditBalance 赠金余额
//     * @param balance       余额
//     * @Desc 分为常规按比例和低分险按比例
//     */
//    private void scaleWithdrawal(BigDecimal tradeAmt, ActivityAccount account, BigDecimal creditBalance, BigDecimal balance) {
//        if (WithdrawalModel.NORMAL.getId() == this.getWithdrawalModel().intValue()) {
//            normalModel(tradeAmt, account, creditBalance);
//        } else if (WithdrawalModel.LOWER.getId() == this.getWithdrawalModel().intValue()) {
//            lowerModel(tradeAmt, account, creditBalance, balance);
//        }
//        //按比例出金需要扣减已发送赠金(已发送赠金减去需要扣减金额)
//        account.setAlreadyCredit(account.getAlreadyCredit().subtract(account.getOperAmount()));
//    }

    /**
     * 低分险按比例出金
     *
     * @param tradeAmt      交易金额
     * @param account       报名数据
     * @param creditBalance 赠金余额
     * @Desc 低分险按比例如果比例不平衡，扣除所有赠金，状态为禁用
     */
    private void lowerModel(BigDecimal tradeAmt, ActivityAccount account, BigDecimal creditBalance, BigDecimal balance) {
        if (balance.multiply(BigDecimal.valueOf(Double.valueOf(this.getAmountProportion()) / 100)).compareTo(creditBalance) < 0) {
            account.setOperAmount(account.getAlreadyCredit());
        } else {
            normalModel(tradeAmt, account, creditBalance);
        }
    }

    /**
     * 常规按比例出金
     *
     * @param tradeAmt      交易金额
     * @param account       报名数据
     * @param creditBalance 赠金余额
     * @Desc 常规按比例出金，赠金余额不为0可一直扣，扣为0数据禁用
     */
    private void normalModel(BigDecimal tradeAmt, ActivityAccount account, BigDecimal creditBalance) {
        //计算按比例出金扣减金额
        BigDecimal multiply = tradeAmt.multiply(BigDecimal.valueOf(Double.valueOf(this.getWithdrawalScale()) / 100));
        log.info("ActivityRule-normalModel-tradeAmt:{},-creditBalance:{},-multiply:{}", tradeAmt, creditBalance, multiply);
        if (account.getAlreadyCredit().compareTo(BigDecimal.ZERO) >= 0 && account.getAlreadyCredit().compareTo(multiply) >= 0) {
            account.setOperAmount(multiply);
        } else if (account.getAlreadyCredit().compareTo(BigDecimal.ZERO) >= 0 && account.getAlreadyCredit().compareTo(multiply) < 0) {
            account.setOperAmount(account.getAlreadyCredit());
        } else {
            account.setOperAmount(BigDecimal.ZERO);
        }
        log.info("ActivityRule-normalModel-account:{}", JSON.toJSONString(account));
    }

    /**
     * 常规出金逻辑
     *
     * @param account       报名数据
     * @param creditBalance 赠金余额
     * @Desc 常规出金计算逻辑（扣减赠金余额，数据状态改为禁用）
     */
    private void normalWithdrawal(ActivityAccount account, BigDecimal creditBalance) {
        account.setOperAmount(account.getAlreadyCredit());
        account.setAlreadyCredit(account.getAlreadyCredit());
        account.setAccountStatus(Boolean.FALSE);
    }
}
