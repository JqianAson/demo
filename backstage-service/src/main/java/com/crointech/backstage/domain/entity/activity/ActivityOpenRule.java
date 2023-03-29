package com.crointech.backstage.domain.entity.activity;

import com.crointech.croincommon.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason Q
 * @create 2021-10-26 20:23
 * @Description 活动规则
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityOpenRule extends BaseEntity {

    private static final long serialVersionUID = 5184551281040602624L;
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
     * 账户筛选（0：全部账号，1：主交易账号，2：同名账户）
     */
    private Integer accountSelect;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 活动总赠金
     */
    private BigDecimal amountSum;

    /**
     * 单个账号赠金上限
     */
    private BigDecimal accountAmountCeiling;

    /**
     * 完成交易手数
     */
    private Integer tradingNumberProportion;

    /**
     * 交易有效期
     */
    private Integer tradingValidityDay;

    /**
     * 出金或转账（转出）是否结束活动  -> 针对参加活动的用户，并不是结束活动
     * 参考枚举 EndIdentityType
     */
    private List<Integer> endIdentity;

    /**
     * 代理类型 1、全部人员 2、指定代理
     */
    private Integer agentType;

    /**
     * 可抗亏损比例
     */
    private Integer lossProportion;

    /**
     * 释放金额
     */
    private BigDecimal freeAmount;

    /**
     * FreeType 释放状态（1：自动释放，2：人工释放）
     */
    private Integer freeType;

    /**
     * FreeStandardType 释放标准类型（1：净值，2：余额）
     */
    private Integer freeStandardType;

    /**
     * 释放标准
     */
    private BigDecimal freeStandardAmount;

    /**
     * 交易组类型（0：筛选交易组，1：全部交易组（全部交易组代表所有server下的所有交易组））
     */
    private Integer tradingGroupType;

    /**
     * 交易组筛选类型（0：参与，1：排除）
     */
    private Integer tradingGroupScreeningType;

}
