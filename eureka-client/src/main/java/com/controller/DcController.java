package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzg
 * @Description
 */
@RestController
public class DcController {

    /**
     *  Spring Cloud Commons 提供的抽象
     *  最早的时候服务发现注册都是通过DiscoveryClient来实现的，随着版本变迁把DiscoveryClient服务注册抽离出来变成了ServiceRegistry抽象，专门负责服务注册，
     *  DiscoveryClient专门负责服务发现。还提供了负载均衡的发现LoadBalancerClient抽象。
     *  DiscoveryClient通过@EnableDiscoveryClient的方式进行启用。
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        System.out.println("EurekaClientRun 进来咯~");
        Thread.sleep(5000L);
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

}