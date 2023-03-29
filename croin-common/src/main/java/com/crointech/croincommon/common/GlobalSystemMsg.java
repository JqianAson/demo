package com.crointech.croincommon.common;

/**
 * 全局系统异常
 * 该类不对外开放，因为业务中通过返回成功返回体即可
 */
public enum GlobalSystemMsg implements BusinessEnumIfc {

    /**
     * 全局异常信息
     */
    /**
     *
     */
    OK("MSCS0000", "请求成功"),
    SYSTEM_ERROR("MSCE9999", "请求失败"),
    URI_NOT_EXIST("MSCE9998", "接口不存在！"),
    CPP_VISIT("MSCE9997", "远程访问CPP接口参数无效！"),
    COMMISSION_VISIT("MSCE9996", "远程访问COMMISSION接口参数无效！"),
    UPDATE_DATA_ERROR("MSCE9995", "更新数据失败！"),
    SAVE_DATA_ERROR("MSCE9994", "保存数据失败！"),
    ;


    private final String errorCode;

    private final String errorMsg;

    GlobalSystemMsg(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getBusRespCode() {
        return errorCode;
    }

    @Override
    public String getRespMsg() {
        return errorMsg;
    }
}
