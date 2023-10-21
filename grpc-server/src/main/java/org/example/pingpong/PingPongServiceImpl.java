package org.example.pingpong;

import io.grpc.stub.StreamObserver;
import org.example.pingpong.stubs.PingPongServiceGrpc;
import org.example.pingpong.stubs.PingRequest;
import org.example.pingpong.stubs.PongResponse;

public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {

    @Override
    public void pingPong(PingRequest req, StreamObserver<PongResponse> responseObserver) {
        System.out.println("Received: " + req.getMessage() + " at " + req.getTimestamp());
        PongResponse reply = PongResponse.newBuilder()
                .setMessage("Pong")
                .setTimestamp(System.currentTimeMillis())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
