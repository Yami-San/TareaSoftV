package co.edu.udem.isv.grpcclient;

import co.edu.udem.isv.grpc.HelloReply;
import co.edu.udem.isv.grpc.HelloRequest;
import co.edu.udem.isv.grpc.MyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import co.edu.udem.isv.grpc.OperacionRequest;
import co.edu.udem.isv.grpc.OperacionReply;

public class GrpcClientApplication {


    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        MyServiceGrpc.MyServiceBlockingStub stub
                = MyServiceGrpc.newBlockingStub(channel);
        HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder()
                .setName("foo")
                .setLastname("bar")
                .setAge(20)
                .build());
        System.out.println(helloReply.getMessage());

        OperacionReply sumaReply = stub.sumar(OperacionRequest.newBuilder()
                .setNumero1(5)
                .setNumero2(3)
                .build());
        System.out.println("Suma: " + sumaReply.getResultado());

        OperacionReply restaReply = stub.restar(OperacionRequest.newBuilder()
                .setNumero1(10)
                .setNumero2(7)
                .build());
        System.out.println("Resta: " + restaReply.getResultado());

        OperacionReply multiplicacionReply = stub.multiplicar(OperacionRequest.newBuilder()
                .setNumero1(4)
                .setNumero2(6)
                .build());
        System.out.println("Multiplicación: " + multiplicacionReply.getResultado());

        OperacionReply divisionReply = stub.dividir(OperacionRequest.newBuilder()
                .setNumero1(20)
                .setNumero2(5)
                .build());
        System.out.println("División: " + divisionReply.getResultado());

        channel.shutdown();
    }

}
