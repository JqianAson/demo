package com.crointech.croincommon.util;

import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class WebUtil {


    /**
     * get http reqeust
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取图片访问路径
     *
     * @param path oss地址
     * @return
     */
    public static String getFilePath(String path) {
        HttpServletRequest request = getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + "/common/file/seeImg?path=" + path;
    }

    public static String getRemoteIp() {
        String ip = null;
        HttpServletRequest request = WebUtil.getRequest();
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        log.info("--IP--> {}", ip);
        return ip.contains("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取操作浏览器
     *
     * @return
     */
    public static String getBrowserType() {
        HttpServletRequest request = WebUtil.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        log.info("browser-{}", JSON.toJSONString(browser));
        return browser.getName();
    }

    public static String getHeader(String key) {
        return getRequest().getHeader(key);
    }

    public static void printHeaders() {
        HttpServletRequest req = getRequest();
        var headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            log.info("打印headers: {} -> {}", headerName, req.getHeader(headerName));
        }
    }

    public static Integer getIntHeader(String key) {
        return getRequest().getIntHeader(key);
    }

    public static String getAttribute(String key) {
        return getRequest().getAttribute(key).toString();
    }

    public static void write(byte[] bytes) throws IOException {
        ServletOutputStream outputStream = getResponse().getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 请求Handler中获取数据
     *
     * @param handlerKey handler中的key
     * @return handler中的value
     */
    public static Object getData(String handlerKey) {
        return getRequest().getHeader(handlerKey);
    }

    /**
     * 获取商户号
     * @return 商户号
     */
    public static String getMerchantCode() {
        return getRequest().getHeader("merchantCode");
    }
}
