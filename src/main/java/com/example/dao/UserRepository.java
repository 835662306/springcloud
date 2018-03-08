package com.example.dao;


import com.example.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 10:37
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNickName(String nickName);

    User findUserByNickNameAndPswd(String nickName, String pswd);
}
