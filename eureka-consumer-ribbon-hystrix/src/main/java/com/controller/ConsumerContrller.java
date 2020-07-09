package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzg
 * @Description
 */
@RestController
public class ConsumerContrller {

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc(){
        System.out.println("EurekaConsumerRibbonHystrixRun : 进来咯");
        return consumerService.consumer();
    }

}