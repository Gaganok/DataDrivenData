package com.cit.data.controller;

import com.cit.data.component.CallableExecutor;
import com.cit.data.component.TestDataComponent;
import com.cit.data.dao.Reddit;
import com.cit.data.dao.Tweet;
import com.cit.data.repository.MongoRepository;
import com.cit.data.service.ConnectionService;
import com.cit.data.service.GRPCService;
import com.cit.data.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.Callable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    private GRPCService grpcService;
    @Autowired
    private HttpService httpService;

    @Autowired
    private TestDataComponent testTweetData;
    @Autowired
    private CallableExecutor executor;

    @Value("${tweet.test.delay}")
    private long tweetDelay;

    @Value("${tweet.test.max}")
    private int maxTweets;

    @GetMapping("/tweet")
    public String tweet(@RequestParam(name = "tweet") String tweetData) {
        Tweet tweet = new Tweet(tweetData);
        mongoRepository.save(tweet);
        grpcService.sendTweet(tweet);
        return "Ok";
    }

    @GetMapping("/test-tweet")
    public String testTweet() throws IOException {
        executor.call(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = testTweetData.getTestFileData();
                Iterator<String> itr = br.lines().iterator();
                for (int i = 0; i < maxTweets; i++) {
                    if (itr.hasNext())
                        grpcService.sendTweet(new Tweet(itr.next()));
                    else break;
                    try {
                        Thread.sleep(tweetDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return "Ok (Tweet)";
    }

    @GetMapping("/test-reddit")
    public String testReddit() throws IOException {
        executor.call(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new StringReader(testTweetData.getTestRedditData()));
                //BufferedReader br = testTweetData.getTestFileData();
                Iterator<String> itr = br.lines().iterator();
                for (int i = 0; i < maxTweets; i++) {
                    if (itr.hasNext()) {
                        try {
                            httpService.sendReddit(new Reddit(itr.next()));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else break;
                    try {
                        Thread.sleep(tweetDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return "Ok (Reddit)";
    }

    @GetMapping("/test")
    public String test() throws IOException {
        testTweet();
        testReddit();
        return "Ok";
    }

    @GetMapping("/complete")
    public String complete() throws IOException {
        grpcService.completeAll();
        return "Ok";
    }

}
