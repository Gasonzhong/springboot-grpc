package com.gason.grpc.server;

import com.gason.grpc.core.docs.GrpcService;
import com.gason.grpc.core.launcher.GrpcLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class GrpcServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(GrpcServerApplication.class, args);
        Map<String, Object> grpcServiceBeanMap =  configurableApplicationContext.getBeansWithAnnotation(GrpcService.class);
        Integer grpcServerPort=configurableApplicationContext.getEnvironment().getProperty("grpc.server.port",Integer.class);
        GrpcLauncher grpcLauncher =new GrpcLauncher();
        grpcLauncher.grpcStart(grpcServiceBeanMap,grpcServerPort);
    }

}
