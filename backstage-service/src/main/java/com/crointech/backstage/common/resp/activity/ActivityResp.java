package com.crointech.backstage.common.resp.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jason Q
 * @create 2021-06-29 13:20
 * @Description 活动列表返回体
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ActivityResp", description = "活动列表返回体")
public class ActivityResp {

    @ApiModelProperty(value = "ID", name = "id")
    private Integer id;

    @ApiModelProperty(value = "活动ID", name = "activityId")
    private String activityId;

    @ApiModelProperty(value = "活动名称", name = "title")
    private String title;

    @ApiModelProperty(value = "活动类型", name = "templateType", required = true)
    private String templateType;

    @ApiModelProperty(value = "活动状态 0、已保存 1、待开始 2、进行中 3、已结束", name = "status")
    private Integer status;

    @ApiModelProperty(value = "活动开始时间", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "活动结束时间", name = "endTime")
    private String endTime;

    @ApiModelProperty(value = "可视状态", name = "visible")
    private Boolean visible;

    @ApiModelProperty(value = "最后编辑人", name = "optUser")
    private String optUser;

    @ApiModelProperty(value = "最后编辑时间", name = "optTime")
    private String optTime;

    @ApiModelProperty(value = "活动子类型", name = "subType", required = true)
    private String subType;

}
