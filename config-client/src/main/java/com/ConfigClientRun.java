package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zzg
 * @Description
 */
@SpringBootApplication
public class ConfigClientRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientRun.class)
                .web(true).run(args);
    }
}