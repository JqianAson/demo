package com.crointech.croincommon.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;


/**
 * 默认全局配置
 */
@Import(BaseConfigure.class)
public class GlobalWebConfig {


    /**
     * http-json转化器
     * @param configProperties
     * @return
     */
    @Bean
    public HttpMessageConverter createHttpMessageConverter(ConfigProperties configProperties){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializerFeature[] serializerFeatures = configProperties.getSerializerFeatures() == null ? new SerializerFeature[0] : configProperties.getSerializerFeatures().toArray(new SerializerFeature[0]);
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }

}
