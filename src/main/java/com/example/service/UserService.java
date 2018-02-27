package com.example.service;

import com.example.entry.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/27 11:21
 * @Version 1.0
 */
@Mapper
public interface UserService {
    List<User> getAll();

    User getOne(Integer id);
}
