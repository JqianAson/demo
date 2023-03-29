package com.crointech.backstage.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Jason Q
 * @create 2021-07-08 16:19
 * @Description 品种组请求体
 **/
@Data
@ApiModel(value = "SpeciesServerResp", description = "品种组请求体")
public class SpeciesServerDto {

    @ApiModelProperty(value = "当前选中的ServerId", name = "serverId")
    private Integer serverId;

    @ApiModelProperty(value = "当前选中的ServerId下选中的symbol名称集合", name = "serverId")
    private List<String> symbolGroup;
}
