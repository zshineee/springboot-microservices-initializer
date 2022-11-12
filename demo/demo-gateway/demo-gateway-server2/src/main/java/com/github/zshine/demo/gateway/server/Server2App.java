package com.github.zshine.demo.gateway.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server2App {

    public static void main(String[] args) {
        SpringApplication.run(Server2App.class);
    }

    @GetMapping("/get")
    public String get() {
        return "this is server2";
    }
}
