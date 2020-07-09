package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zzg
 * @Description
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerGitRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerGitRun.class)
                .web(true).run(args);
    }
}