package com.crointech.backstage.common.resp.activity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hp
 * @create 2021-11-16
 * @Description 活动详情返回体
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ActivityDetailResp", description = "活动详情返回体")
public class ActivityDetailResp {

    @ApiModelProperty(value = "活动ID", name = "activityId")
    private String activityId;

    @ApiModelProperty(value = "模板类型", name = "templateType")
    private Integer templateType;

    @ApiModelProperty(value = "赠金类型 1、入金赠金 2、开户赠金", name = "subType", required = true)
    private Integer subType;

    @ApiModelProperty(value = "活动名称", name = "title")
    private String title;

    @ApiModelProperty(value = "活动状态 0、已保存 1、待开始 2、进行中 3、已结束", name = "status")
    private Integer status;

    @ApiModelProperty(value = "活动开始时间", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "活动结束时间", name = "endTime")
    private String endTime;

    @ApiModelProperty(value = "可视状态", name = "visible")
    private Boolean visible;

    @ApiModelProperty(value = "活动地址", name = "url")
    private String url;

    @ApiModelProperty(value = "banner", name = "banner")
    private String banner;

    @ApiModelProperty(value = "M站banner", name = "smallBanner")
    private String smallBanner;

    @ApiModelProperty(value = "活动备注", name = "remark")
    private String remark;

    @ApiModelProperty(value = "活动是否重叠（传入重叠的活动ID，如未勾选则传空集合）", name = "overlap")
    private List<Integer> overlap;

    @ApiModelProperty(value = "活动规则", name = "ruleDetailResp")
    private JSONObject ruleDetailResp;

}
