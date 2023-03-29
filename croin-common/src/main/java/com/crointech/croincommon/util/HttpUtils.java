package com.crointech.croincommon.util;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Stephen Suen
 * @date 2021-03-30 6:03 下午
 * 做咩呀???
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
@Slf4j
public class HttpUtils {


    public static String POST(String url, String[] headers, byte[] data) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        HttpRequest httpRequest = HttpRequest
            .newBuilder(URI.create(url))
            .version(HttpClient.Version.HTTP_2)
            .POST(HttpRequest.BodyPublishers.ofByteArray(data))
            .headers(headers)
            .build();
        return request(httpRequest);
    }

    public static String GET(String url, String[] headers) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        HttpRequest httpRequest = HttpRequest
            .newBuilder(URI.create(url))
            .version(HttpClient.Version.HTTP_2)
            .GET()
            .headers(headers)
            .build();
        return request(httpRequest);
    }

    private static String request(HttpRequest httpRequest) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        var httpClient = HttpClient.newBuilder()
            .sslContext(sslContext)
            .build();

        var result = httpClient
            .send(httpRequest, HttpResponse.BodyHandlers.ofString())
            .body();
        log.info("发送http请求2:{}:{}, headers:{}", httpRequest.method(), httpRequest.uri(), httpRequest.headers().toString());
        log.info("发送http请求2结果:{}", result);
        return result;
    }

    private static final TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
        }
    };

}
