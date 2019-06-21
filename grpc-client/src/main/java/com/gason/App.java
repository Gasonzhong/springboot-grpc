package com.gason;

import com.gason.grpc.api.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static String host = "localhost";

    public static Integer port = 7000;

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            GasonMessageGrpc.GasonMessageBlockingStub stub = GasonMessageGrpc.newBlockingStub(channel);
            Header header=Header.newBuilder().setAuth("zjs123456").build();
            Body body=Body.newBuilder().setData("我是客户端").build();
            GasonRequest request = GasonRequest.newBuilder()
                    .setHeader(header)
                    .setBody(body)
                    .build();
            GasonResponse response = stub.onmessage(request);
            System.out.println(response.getBody().getData());

        }
        finally {
            channel.awaitTermination(10,TimeUnit.SECONDS);
        }
    }
}
