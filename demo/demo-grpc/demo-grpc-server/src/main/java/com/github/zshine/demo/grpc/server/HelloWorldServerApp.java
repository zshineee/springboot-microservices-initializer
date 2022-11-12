package com.github.zshine.demo.grpc.server;

import com.github.zshine.demo.grpc.lib.proto.helloworld.GreeterGrpc;
import com.github.zshine.demo.grpc.lib.proto.helloworld.HelloReply;
import com.github.zshine.demo.grpc.lib.proto.helloworld.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class HelloWorldServerApp {

    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8081)
                .addService(new GreeterImpl())  //这里可以添加多个模块
                .build()
                .start();
        server.awaitTermination();
    }





    private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }


    }
}
