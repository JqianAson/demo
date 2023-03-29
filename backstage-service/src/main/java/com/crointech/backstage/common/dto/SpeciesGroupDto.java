package com.crointech.backstage.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hp
 * @create 2021-11-17
 * @Description 品种组Server响应体
 **/
@Data
@ApiModel(value = "SpeciesGroupResp", description = "品种组Server响应体")
public class SpeciesGroupDto {

    @ApiModelProperty(value = "当前选中品种组", name = "species")
    private Integer speciesId;

    @ApiModelProperty(value = "当前选中的ServerId下选中的品种组集合", name = "servers")
    private List<SpeciesServerDto> servers;
}
