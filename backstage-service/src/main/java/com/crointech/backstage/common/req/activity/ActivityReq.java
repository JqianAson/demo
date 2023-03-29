package com.crointech.backstage.common.req.activity;

import com.crointech.croincommon.common.GlobalPageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jason Q
 * @create 2021-06-29 13:21
 * @Description 活动列表请求体
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ActivityReq", description = "活动列表请求体")
public class ActivityReq extends GlobalPageBase {

    private static final long serialVersionUID = 1446452713857383383L;

    @ApiModelProperty(value = "活动名称", name = "title")
    private String title;

    @ApiModelProperty(value = "活动状态", name = "status")
    private Integer status;

    @ApiModelProperty(value = "可视状态", name = "visible")
    private Boolean visible;
}
