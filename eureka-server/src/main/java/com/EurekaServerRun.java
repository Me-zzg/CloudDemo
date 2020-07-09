package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zzg
 * @Description
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerRun.class)
                .web(true).run(args);
    }

}