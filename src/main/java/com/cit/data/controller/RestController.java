package com.cit.data.controller;

import com.cit.data.component.TestDataComponent;
import com.cit.data.dao.Tweet;
import com.cit.data.repository.MongoRepository;
import com.cit.data.service.GRPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.Iterator;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired private ResourceLoader resourceLoader;
    @Autowired private MongoRepository mongoRepository;
    @Autowired private GRPCService service;
    @Autowired private TestDataComponent testTweetData;

    @Value("${tweet.test.delay}")
    private long tweetDelay;

    @GetMapping("/tweet")
    public String tweet(@RequestParam(name = "tweet") String tweetData){
        Tweet tweet = new Tweet(tweetData);
        mongoRepository.save(tweet);
        service.sendTweet(tweet);
        return "Ok";
    }

    @GetMapping("/test")
    public String test() throws IOException {
        BufferedReader br = testTweetData.getTestFileData();
        Iterator<String> itr = br.lines().iterator();
        for(int i = 0; i < 50; i++){
            if(itr.hasNext())
                service.sendTweet(new Tweet(itr.next()));
            else break;
            try {
                Thread.sleep(tweetDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Ok";
    }

    @GetMapping("/complete")
    public String complete() throws IOException {
        service.completeAll();
        return "Ok";
    }
}
