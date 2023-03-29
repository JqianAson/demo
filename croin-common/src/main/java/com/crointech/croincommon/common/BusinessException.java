package com.crointech.croincommon.common;


/**
 * 全局业务异常，用于向页面返回业务错误提示信息
 * @author Devin.qi < devin.qi@ixsecurities.com />
 */
public class BusinessException extends RuntimeException {


    /**
     * 业务异常详细信息
     */
    private BusinessEnumIfc businessEnumIfc;

    /**
     * 部分code需要携带错误参数
     */
    private Object data;

    public BusinessException(BusinessEnumIfc aBusinessEnumIfc){
        super(String.format("业务异常！code: %s, msg: %s", aBusinessEnumIfc.getBusRespCode(), aBusinessEnumIfc.getRespMsg()));
        businessEnumIfc = aBusinessEnumIfc;
    }

    public BusinessException(BusinessEnumIfc aBusinessEnumIfc, Object aData){
        this.businessEnumIfc = aBusinessEnumIfc;
        this.data = aData;
    }

    /**
     *
     * @return
     */
    public BusinessEnumIfc getBusinessEnumIfc() {
        return businessEnumIfc;
    }

    public Object getData() {
        return data;
    }
}
