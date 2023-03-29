package com.crointech.backstage.domain.entity.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jason Q
 * @create 2023-01-13 15:05
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySubsidiesRule {

    //活动ID
    private String activityId;

    //下发类型（1：手动下发，2：自动下发）
    private Integer sendType;

    //是否支持批量特批（默认不支持，即false）
    private Boolean batchApply;

    //是否支持重复报名
    private Boolean repeatApply;

    //金额类型（1：赠金，2：余额）
    private Integer amountType;

    //附件数量
    private Integer fileNum;

    //附件是否必传
    private Boolean fileMust;
}
