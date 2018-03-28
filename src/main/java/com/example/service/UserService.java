package com.example.service;

import com.example.entry.BpUser;
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
//@FunctionalInterface
public interface UserService {
    List<BpUser> getAll();

    BpUser getOne(Integer id);
}
