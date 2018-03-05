package com.example.dao;

import com.example.entry.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 16:28
 * @Version 1.0
 */
public interface UserMongoRepository extends MongoRepository<UserMongo, Integer>{

    UserMongo findByName(String name);
}
