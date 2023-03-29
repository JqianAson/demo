package com.crointech.backstage.domain.entity.activityaccount;

import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Demo交易账号记录表
 *
 * @author jasonqian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDemoAccount extends BaseEntity implements Serializable {

    /**
     * 商户号
     */
    private Integer merchantId;

    /**
     * 关联表主键
     */
    private Integer activityAccountId;

    /**
     * demo账号关联真实交易账号用户ID
     */
    private Integer userId;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * demo交易账号
     */
    private Integer demoAccount;

    /**
     * demo账号server
     */
    private Integer server;

    /**
     * demo账号交易组
     */
    private String tradeGroup;

    /**
     * 账号在活动内的参与状态，0:禁用，1:启用
     */
    private Boolean status;

    /**
     * 报名时间
     */
    private Date applyTime;

    /**
     * 交易有效期
     */
    private Date cumulativeVolumeTime;

    /**
     * 报名后下发的赠金数量
     */
    private BigDecimal credit;

    /**
     * 报名后统计的交易手数
     */
    private BigDecimal volume;

    /**
     * 报名方式，1、活动报名 2、特批报名
     */
    private Short source;

    private static final long serialVersionUID = 1L;
//
//    /**
//     * 新增记录
//     *
//     * @param account            活动账户
//     * @param newDemoAccountInfo 新增的demo账号信息
//     * @param demoCredit         demo账号赠金
//     * @param server             demo账号server
//     * @param group              demo账号交易组
//     */
//    public void add(ActivityAccount account, ClientsDto newDemoAccountInfo, BigDecimal demoCredit, Integer server, String group) {
//        this.setMerchantId(account.getMerchantsId());
//        this.setActivityAccountId(account.getId());
//        this.setUserId(account.getUserId());
//        this.setActivityId(account.getActivityId());
//        this.setDemoAccount(newDemoAccountInfo.getLogin());
//        this.setServer(server);
//        this.setTradeGroup(group);
//        this.setStatus(true);
//        this.setApplyTime(account.getApplyActivityTime());
//        this.setCumulativeVolumeTime(account.getCumulativeVolumeTime());
//        this.setCredit(demoCredit);
//        this.setVolume(BigDecimal.ZERO);
//        this.setSource(account.getSource().shortValue());
//        this.setCreateTime(account.getCreateTime());
//        this.setCreateUser(account.getCreateUser());
//        this.setDel(false);
//    }

    public Boolean checkRealAccountDepositAndDemoBalance(BigDecimal depositAmountSum) {
        return false;
    }
}