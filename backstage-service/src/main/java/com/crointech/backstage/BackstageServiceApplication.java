package com.crointech.backstage;

import com.crointech.croincommon.config.GlobalWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.crointech.backstage"})
@ComponentScan(value = {"com.crointech.*"})
@EnableFeignClients(basePackages = {"com.crointech.*"})
@Import({GlobalWebConfig.class})
@EnableTransactionManagement
/**
 * @author Jason Q
 * @create 2021-11-01 16:04
 * @Description 程序启动入口
 **/
public class BackstageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackstageServiceApplication.class, args);
    }

}
