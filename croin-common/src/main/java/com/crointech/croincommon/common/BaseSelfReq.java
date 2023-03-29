package com.crointech.croincommon.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * RSA安全认证公共请求类
 */
@Data
public class BaseSelfReq {

    /**
     * 公钥
     */
    @NotBlank
    private String aesData;

    /**
     * RSA 加密数据
     */
    @NotBlank
    private String rsaData;
}
