package com.github.zshine.demo.bus.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@SpringBootApplication
@RestController
@Slf4j
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class);
    }

    @Bean
    public Consumer<String> error() {
        return s -> log.info("广播异常消息：{}", s);
    }

    @Bean
    public Consumer<String> debug() {
        return s -> log.info("广播调试消息：{}", s);
    }
}
