package com.example.dao;


import com.example.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 10:37
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT User from User u where u.nickName = ?1")
    User getUserByName(String username);
}
