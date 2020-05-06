package com.cit.data.service;

import com.cit.data.dao.Tweet;
import com.cit.data.grcp.TweetRequest;
import com.cit.data.grcp.TweetResponse;
import com.cit.data.grcp.TweetServiceGrpc;
import com.cit.data.grcp.TweetServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class GRPCService extends TweetServiceGrpc.TweetServiceImplBase{

    @Value("${grpc.tweet.server}")
    private int port;

    Logger logger = LoggerFactory.getLogger(GRPCService.class);

    private GRPCService(){}

    @PostConstruct
    private void postStart() throws IOException, InterruptedException {
        start();
        logger.info("GRPC Server started on port: " + port);
    }

    private void start() throws IOException, InterruptedException {
        Thread serviceThread = new Thread(() -> {
            Server server = ServerBuilder.forPort(port).addService(this)
                    .build();
            try {
                server.start();
                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        serviceThread.start();
    }

    private Set<StreamObserver<TweetResponse>> observers = new LinkedHashSet<>();

    public void sendTweet(Tweet tweet){
        Iterator<StreamObserver<TweetResponse>> itr = observers.iterator();
        while (itr.hasNext()){
            StreamObserver<TweetResponse> observer = itr.next();
            try {
                observer.onNext(tweet.getTweetResponce());
            } catch (io.grpc.StatusRuntimeException e){
                itr.remove();
            }
        }
    }

    public void completeAll(){
        observers.forEach(ob -> ob.onCompleted());
    }

    @Override
    public void request(TweetRequest request, StreamObserver<TweetResponse> responseObserver) {
        observers.add(responseObserver);
    }
}
