package com.cit.data.dao;

import com.cit.data.grcp.TweetResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Tweet implements Serializable {

    int polarity;
    @Id
    long id;
    String date;
    String queryFlag;
    String user;
    String tweet;

    public Tweet(String tweetEntry){
        tweetEntry = tweetEntry.replace("\"", "");
        String[] params = tweetEntry.split(",");
        polarity = Integer.parseInt(params[0]);
        id = Long.parseLong(params[1]);
        date = params[2];
        queryFlag = params[3];
        user = params[4];
        tweet = params[5];
    }

    public Tweet(int polarity, int id, String date, String queryFlag, String user, String tweet) {
        this.polarity = polarity;
        this.id = id;
        this.date = date;
        this.queryFlag = queryFlag;
        this.user = user;
        this.tweet = tweet;
    }

    public TweetResponse getTweetResponce(){
        return TweetResponse.newBuilder()
                .setId(getId())
                .setData(getDate())
                .setUser(getUser())
                .setTweet(getTweet())
                .build();
    }


    @Override
    public String toString() {
        return String.format(
                "Tweet[id=%s, date='%s', user='%s', tweet='%s'",
                id, date, user, tweet);
    }
}
