package com.crointech.backstage.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hp
 * @create 2021-11-17
 * @Description 交易组server响应体
 **/
@Data
@ApiModel(value = "TradingServerDto", description = "交易组server响应体")
public class TradingServerDto {

    @ApiModelProperty(value = "当前选中的ServerId", name = "serverId")
    private Integer serverId;

    @ApiModelProperty(value = "是否为全部（0，不是全部，1：全部）", name = "allTradingGroupTag", example = "0")
    private Boolean allTradingGroupTag;

    @ApiModelProperty(value = "当前Server下选中的交易组集合", name = "tradingGroup")
    private List<String> tradingGroup;
}
