package co.edu.udem.isv.grpcserver;

import co.edu.udem.isv.grpcserver.service.MyServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;


public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new MyServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }

}
