package com.gason;

import com.gason.grpc.api.RPCDateRequest;
import com.gason.grpc.api.RPCDateResponse;
import com.gason.grpc.api.RPCDateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

import static com.gason.grpc.api.RPCDateServiceGrpc.*;

/**
 * @ClassName: RpcApp
 * @auther: zhongjias
 * @date: 2019/6/21 08:57
 * @description:
 */
public class RpcApp {
    private static final String host = "localhost";
    private static final int serverPort = 7000;

    public static void main( String[] args ) throws Exception {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress( host, serverPort ).usePlaintext().build();
        try {
            RPCDateServiceBlockingStub rpcDateService = newBlockingStub( managedChannel );
            RPCDateRequest rpcDateRequest = RPCDateRequest
                    .newBuilder()
                    .setUserName("hansonwang99")
                    .build();
            RPCDateResponse rpcDateResponse = rpcDateService.getDate( rpcDateRequest );
            System.out.println( rpcDateResponse.getServerDate() );
        } finally {
                managedChannel.awaitTermination(2,TimeUnit.SECONDS);
        }
    }
}
