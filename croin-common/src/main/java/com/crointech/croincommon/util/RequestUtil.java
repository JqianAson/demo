package com.crointech.croincommon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crointech.croincommon.common.BusinessEnumIfc;
import com.crointech.croincommon.common.BusinessException;
import com.crointech.croincommon.common.GlobalSystemMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;


/**
 * POST and GET util
 *
 * @author laban
 */
@Slf4j
public class RequestUtil {

    public static <T> T httpPost(String url, String jsonStr, Class<T> t) {
        log.info("开始发送post请求: {}, 参数jsonStr:{}", url, jsonStr);
        // 创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        T instance = null;
        try {
            instance = t.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.addHeader("Accept", "application/json");
        // 设置连接超时时间和数据获取超时时间--单位：ms
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000000)
            .setConnectionRequestTimeout(5000000).setSocketTimeout(5000000).build();
        httppost.setConfig(requestConfig);
        // 设置http request body请求体
        if (null != jsonStr) {
            // 解决中文乱码问题
            StringEntity myEntity = new StringEntity(jsonStr, "UTF-8");
            httppost.setEntity(myEntity);
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = EntityUtils.toString(Objects.requireNonNull(response).getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 得到返回的字符串
        log.info("返回结果 :{}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject == null) {
            log.info("远程请求接口返回体异常: resp：null");
            throw new BusinessException(GlobalSystemMsg.SYSTEM_ERROR);
        }
        String code = jsonObject.getString("code");
        if (GlobalSystemMsg.OK.getBusRespCode().equals(code)) {
            if (instance instanceof JSONObject) {
                return (T) jsonObject;
            }
        } else {
            String msg = jsonObject.getString("msg");
            String data = jsonObject.getString("data");
            // 将远程的异常原样抛出
            throw new BusinessException(new BusinessEnumIfc() {
                @Override
                public String getBusRespCode() {
                    return code;
                }

                @Override
                public String getRespMsg() {
                    return msg;
                }
            }, data);
        }

        T resultBean = JSONObject.parseObject(jsonObject.toString(), t);
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * httpget调用http接口
     *
     * @param url
     * @param jsonStr
     * @param t
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T httpGet(String url, String jsonStr, Class<T> t) throws Exception {
        log.info("开始发送Get请求，调用接口:{}", url);
        String result = "";
        T instance = null;
        try {
            instance = t.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建实例方法
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpget.addHeader("Accept", "application/json");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            if (response == null) {
                throw new BusinessException(GlobalSystemMsg.SYSTEM_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        // 如果状态码为200,就是正常返回
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JSONObject jsonObject = JSON.parseObject(result);
        if (instance instanceof JSONObject) {
            return (T) jsonObject;
        }

        T resultBean = (T) JSONObject.parseObject(jsonObject.toString(), t);
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }
}
