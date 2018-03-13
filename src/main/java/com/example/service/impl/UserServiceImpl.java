package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/12 17:01
 * @Version 1.0
 */
@Service
public class UserServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
