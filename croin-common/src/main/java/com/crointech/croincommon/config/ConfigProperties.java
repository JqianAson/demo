package com.crointech.croincommon.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * 项目配置文件
 * @author Devin.qi < devin.qi@ixsecurities.com />
 */
public class ConfigProperties {

    private List<String> logFilterExcludeUris;

    private String allowOrigin;

    private String allowMethods;

    private String allowHeaders;

    private List<SerializerFeature> serializerFeatures;

    private List<Feature> features;


    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<SerializerFeature> getSerializerFeatures() {
        return serializerFeatures;
    }

    public void setSerializerFeatures(List<SerializerFeature> serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }

    public List<String> getLogFilterExcludeUris() {
        return logFilterExcludeUris;
    }

    public void setLogFilterExcludeUris(List<String> logFilterExcludeUris) {
        this.logFilterExcludeUris = logFilterExcludeUris;
    }

    public String getAllowOrigin() {
        return allowOrigin;
    }

    public void setAllowOrigin(String allowOrigin) {
        this.allowOrigin = allowOrigin;
    }

    public String getAllowMethods() {
        return allowMethods;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public String getAllowHeaders() {
        return allowHeaders;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }
}
