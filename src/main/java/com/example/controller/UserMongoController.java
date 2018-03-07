package com.example.controller;

import com.example.dao.UserMongoRepository;
import com.example.entry.UserMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 16:29
 * @Version 1.0
 */
@RestController
public class UserMongoController {
    @Autowired
    private UserMongoRepository userMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * save use before findName
     * @return
     */
    @GetMapping("/save")
    public UserMongo save() {
        UserMongo user = new UserMongo(2, "Tseng", 21);
        mongoTemplate.save(user);
        return user;
    }

    //该方法支持跨域
    @CrossOrigin
    @GetMapping("/find")
    public List<UserMongo> find() {
        List<UserMongo> userList = mongoTemplate.findAll(UserMongo.class);
        return userList;
    }

    /**
     * input String name "Tseng"
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    public UserMongo findByName(@RequestParam("name") String name) {
        UserMongo user = userMongoRepository.findByName(name);
        return user;
    }
}
