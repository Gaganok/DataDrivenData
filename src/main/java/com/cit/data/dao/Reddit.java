package com.cit.data.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Reddit {

    @Id
    String id;
    String title;
    int score;
    String author;
    String author_flair_text;
    String removed_by;
    float total_awards_received;
    String awarders;
    long created_utc;
    String full_link;
    int num_comments;
    boolean over_18;

    private Reddit(){}

    public Reddit(String redditEntry){
        redditEntry = redditEntry.replace("\"", "");
        String[] params = redditEntry.split(",");
        id = params[0];
        title = params[1];
        score = Integer.parseInt(params[2]);
        author = params[3];
        author_flair_text = params[4];
        removed_by = params[5];
        total_awards_received = Float.parseFloat(params[6]);
        awarders = params[7];
        created_utc = Long.parseLong(params[8]);
        full_link = params[9];
        num_comments = Integer.parseInt(params[10]);
        over_18 = Boolean.parseBoolean(params[11]);
    }
}
