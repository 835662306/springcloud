package com.example.entry;

import org.springframework.data.annotation.Id;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 16:26
 * @Version 1.0
 */
public class UserMongo {
    @Id
    private Integer id;
    private String name;
    private int age;
    public UserMongo(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
