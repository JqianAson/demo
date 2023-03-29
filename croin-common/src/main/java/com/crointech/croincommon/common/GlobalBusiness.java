package com.crointech.croincommon.common;

/**
 * 公共对外开放的业务异常
 */
public enum GlobalBusiness implements BusinessEnumIfc {
    PARAM_LOSS("MSAE0001", "参数缺失！"),
    PARAM_UNSUPPORT("MSAE0002", "参数不合法！"),
    USER_NO_EXIST("MSAE0006", "用户不存在"),
    RIBBON_NO_LOADBALANCE("MSAE0003", "no load balance！"),
    PSNE0288("PSNE0288", "未获取到锁!"),
    ;

    private String errorCode;

    private String errorMsg;

    GlobalBusiness(String errorCode, String errorMsg) {
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
