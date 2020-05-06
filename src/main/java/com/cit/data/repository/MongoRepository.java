package com.cit.data.repository;

import com.cit.data.dao.Tweet;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Tweet, String> {
}
