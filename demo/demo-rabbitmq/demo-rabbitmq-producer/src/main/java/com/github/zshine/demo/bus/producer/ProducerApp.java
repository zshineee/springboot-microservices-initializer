package com.github.zshine.demo.bus.producer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class ProducerApp {

    int i = 0;

    int j = 0;

    @Resource
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class);
    }


    @GetMapping("/error")
    public String error() {
        String data = "this is message{" + (i++) + "}";
        streamBridge.send("error-out-0", data);
        return data;
    }

    @GetMapping("/debug")
    public String debug() {
        String data = "this is message{" + (j++) + "}";
        streamBridge.send("debug-out-0", data);
        return data;
    }
}
