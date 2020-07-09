package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author zzg
 * @Description
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class EurekaConsumerFeignRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerFeignRun.class)
                .web(true).run(args);
    }

}