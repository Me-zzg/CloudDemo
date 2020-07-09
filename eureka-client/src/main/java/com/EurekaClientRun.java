package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author zzg
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaClientRun.class)
                .web(true).run(args);
    }
}