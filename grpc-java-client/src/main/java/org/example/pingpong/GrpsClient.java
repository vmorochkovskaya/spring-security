package org.example.pingpong;


//import lombok.extern.slf4j.Slf4j;

import io.grpc.*;
import org.example.pingpong.stubs.PingPongServiceGrpc;
import org.example.pingpong.stubs.PingRequest;
import org.example.pingpong.stubs.PongResponse;


public class GrpsClient {

    private final PingPongServiceGrpc.PingPongServiceBlockingStub blockingStub;

    public GrpsClient(Channel channel) {
        blockingStub = PingPongServiceGrpc.newBlockingStub(channel);
    }

    public void pingPong(String message, long timestamp) {
        System.out.println("Will try to ping ...");
        PingRequest pingRequest = PingRequest.newBuilder()
                .setMessage(message)
                .setTimestamp(timestamp)
                .build();
        PongResponse pongResponse;
        try {
            pongResponse = blockingStub.pingPong(pingRequest);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Response: " + pongResponse.getMessage() + " at " + pongResponse.getTimestamp());
    }

    public static void main(String[] args) {
        String message = "Ping";
        long timestamp = System.currentTimeMillis();
        String target = "localhost:8080";

        ManagedChannel channel = ManagedChannelBuilder
                .forTarget(target)
                .usePlaintext()
                .build();
        try {
            GrpsClient client = new GrpsClient(channel);
            client.pingPong(message, timestamp);
        } finally {
            channel.shutdownNow();
        }
    }
}
