package com.github.zshine.demo.grpc.client;

import com.github.zshine.demo.grpc.lib.proto.helloworld.GreeterGrpc;
import com.github.zshine.demo.grpc.lib.proto.helloworld.HelloReply;
import com.github.zshine.demo.grpc.lib.proto.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloWorldClientApp {


    public static void main(String[] args) {
        //初始化连接
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        //初始化远程服务Stub
        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
        //构造服务调用参数对象
        HelloRequest request = HelloRequest.newBuilder().setName("测试5").build();
        //调用远程服务方法
        HelloReply response = blockingStub.sayHello(request);
        //返回值
        System.out.println(response.getMessage());
    }

}
