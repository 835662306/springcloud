package com.example.dao;

import com.example.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/12 10:54
 * @Version 1.0
 */
public interface UserRepostitory extends JpaRepository<User, Integer> {

}
