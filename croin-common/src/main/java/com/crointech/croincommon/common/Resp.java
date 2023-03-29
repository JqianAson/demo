package com.crointech.croincommon.common;

import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason Q
 * @create 2021-06-29 11:13
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = 4765535308338670054L;

    @ApiModelProperty(value = "状态码", name = "状态码", required = true)
    private String code;

    @ApiModelProperty(value = "状态码描述", name = "状态码描述", required = true)
    private String msg;

    @ApiModelProperty(value = "数据对象", name = "数据对象")
    private T data;

    public final static <T> Resp<T> createSuccessResp(T data) {
        Resp<T> resp = new Resp<>();
        resp.data = data;
        resp.setCode(GlobalSystemMsg.OK.getBusRespCode());
        resp.setMsg(GlobalSystemMsg.OK.getRespMsg());
        resp.setData(data);
        return resp;
    }

    public final static <T> Resp<T> createSuccessResp() {
        return createSuccessResp(null);
    }

    public final static <T> Resp<T> createFailResp(BusinessEnumIfc businessEnumIfc) {
        Resp<T> resp = new Resp<>();
        resp.setCode(businessEnumIfc.getBusRespCode());
        resp.setMsg(businessEnumIfc.getRespMsg());
        return resp;
    }

    public final static <T> Resp<T> createFailResp2(BusinessEnumIfc businessEnumIfc, String defaultMessage) {
        Resp<T> resp = new Resp<>();
        resp.setCode(businessEnumIfc.getBusRespCode());
        resp.setMsg(StringUtils.isNotBlank(defaultMessage) ? defaultMessage : businessEnumIfc.getRespMsg());
        return resp;
    }

    /**
     * 创建请求失败响应体
     *
     * @return Resp
     */
    public final static <T> Resp<T> createFailResp() {
        Resp<T> resp = new Resp<>();
        resp.setCode(GlobalSystemMsg.SYSTEM_ERROR.getBusRespCode());
        resp.setMsg(GlobalSystemMsg.SYSTEM_ERROR.getRespMsg());
        return resp;
    }
}
