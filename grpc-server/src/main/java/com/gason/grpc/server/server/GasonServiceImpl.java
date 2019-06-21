package com.gason.grpc.server.server;

import com.gason.grpc.api.*;
import com.gason.grpc.core.docs.GrpcService;
import io.grpc.stub.StreamObserver;

/**
 * @ClassName: GasonServiceImpl
 * @auther: zhongjias
 * @date: 2019/6/20 19:56
 * @description:
 */
@GrpcService
public class GasonServiceImpl extends GasonMessageGrpc.GasonMessageImplBase {
    @Override
    public void onmessage(GasonRequest request, StreamObserver<GasonResponse> responseObserver) {
        GasonResponse gasonResponse = null;
        try {
            System.out.println("auth:"+request.getHeader().getAuth());
            System.out.println("data:"+request.getBody().getData());
            gasonResponse = GasonResponse
                    .newBuilder()
                    .setHeader(Header.newBuilder().setAuth(request.getHeader().getAuth()).build())
                    .setBody(Body.newBuilder().setData("收到内容").build())
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext( gasonResponse );
        }
        responseObserver.onCompleted();
    }
}
