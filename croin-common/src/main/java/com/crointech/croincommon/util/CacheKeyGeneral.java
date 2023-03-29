package com.crointech.croincommon.util;

/**
 * @author Stephen Suen
 * @date 2022-01-21 5:59 PM
 * 做咩呀???
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class CacheKeyGeneral {


    public static final int LOGIN_EXPIRE = 30;

    /**
     * 登陆token 缓存key
     */
    public static String tokenKey(String token){
        return "com:crointech:permission:" + token;
    }

    /**
     * 单点登录 key
     * @param userId
     * @return
     */
    public static String singleLoginKey(String userId){
        return "single:user:" + userId;
    }


}
