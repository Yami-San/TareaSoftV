package co.edu.udem.isv.grpcserver.service;


import co.edu.udem.isv.grpc.HelloReply;
import co.edu.udem.isv.grpc.HelloRequest;
import co.edu.udem.isv.grpc.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
import co.edu.udem.isv.grpc.OperacionRequest;
import co.edu.udem.isv.grpc.OperacionReply;

public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + request.getName() + " " + request.getLastname() + " : " + request.getAge())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sumar(OperacionRequest request, StreamObserver<OperacionReply> responseObserver) {
        int resultado = request.getNumero1() + request.getNumero2();
        OperacionReply reply = OperacionReply.newBuilder()
                .setResultado(resultado)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void restar(OperacionRequest request, StreamObserver<OperacionReply> responseObserver) {
        int resultado = request.getNumero1() - request.getNumero2();
        OperacionReply reply = OperacionReply.newBuilder()
                .setResultado(resultado)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void multiplicar(OperacionRequest request, StreamObserver<OperacionReply> responseObserver) {
        int resultado = request.getNumero1() * request.getNumero2();
        OperacionReply reply = OperacionReply.newBuilder()
                .setResultado(resultado)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void dividir(OperacionRequest request, StreamObserver<OperacionReply> responseObserver) {
        if (request.getNumero2() != 0) {
            int resultado = request.getNumero1() / request.getNumero2();
            OperacionReply reply = OperacionReply.newBuilder()
                    .setResultado(resultado)
                    .build();
            responseObserver.onNext(reply);
        } else {
            // Si el segundo número es cero, enviamos un mensaje de error
            responseObserver.onError(
                    new IllegalArgumentException("No se puede dividir entre cero.")
            );
        }
        responseObserver.onCompleted();
    }

}
