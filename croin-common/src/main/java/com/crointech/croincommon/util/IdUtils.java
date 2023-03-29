package com.crointech.croincommon.util;

import java.util.UUID;

/**
 * id获取工具类
 */
public class IdUtils {

    /**
     * 暂时使用uuid
     * @return
     */
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
