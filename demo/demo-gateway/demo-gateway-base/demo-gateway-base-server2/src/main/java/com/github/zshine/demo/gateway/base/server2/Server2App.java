package com.github.zshine.demo.gateway.base.server2;


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
