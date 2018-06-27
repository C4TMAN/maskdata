package com.c4tman.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhangxiaoman on 2017/10/24.
 */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);
    }

}
