package com.crointech.croincommon.util;

import com.crointech.croincommon.common.GlobalSystemMsg;
import com.crointech.croincommon.common.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * resp脱壳工具类
 */
@Slf4j
public class RespUtil {

    /**
     * 脱壳校验
     *
     * @param resp 响应体
     * @return
     */
    public static boolean checkResp(Resp resp) {
        if (null == resp) {
            log.info("RespUtil-checkResp-resultIsNull");
            return Boolean.FALSE;
        }
        if (!GlobalSystemMsg.OK.getBusRespCode().equals(resp.getCode())) {
            log.info("RespUtil-checkResp-resultIsFail-{}", resp.getMsg());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 脱壳校验（数据data）
     *
     * @param resp 响应体
     * @return
     */
    public static boolean checkDataResp(Resp resp) {
        if (null == resp) {
            log.info("RespUtil-checkDataResp-resultIsNull");
            return Boolean.FALSE;
        }
        if (!GlobalSystemMsg.OK.getBusRespCode().equals(resp.getCode())) {
            log.info("RespUtil-checkDataResp-resultIsFail-{}", resp.getMsg());
            return Boolean.FALSE;
        }
        if (null == resp.getData()) {
            log.info("RespUtil-checkDataResp-DataIsNull");
            return Boolean.FALSE;
        }
        if (resp.getData() instanceof Collection && CollectionUtils.isEmpty((Collection) resp.getData())) {
            log.info("RespUtil-checkDataResp-resultIsEmpty");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 脱壳校验并获取返回值
     *
     * @param resp 响应体
     * @return
     */
    public static <T> T getData(Resp resp) {
        if (!checkDataResp(resp)) {
            return null;
        }
        return (T) resp.getData();
    }
}
