package com.cit.data.grcp;

import com.cit.data.dao.Tweet;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class TweetServiceImpl extends TweetServiceGrpc.TweetServiceImplBase {

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
