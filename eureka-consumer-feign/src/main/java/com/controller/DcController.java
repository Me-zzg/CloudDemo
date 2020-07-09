package com.controller;

import com.feign.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzg
 * @Description
 */
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("consumer")
    public String dc(){
       return dcClient.consumer();
    }
}