package com.example.entry;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/12 10:13
 * @Version 1.0
 */
@Entity
@Table(name = "bp_user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 20, unique = true ,nullable = false) //唯一 不能为 null
    private String username;

    @Column(length = 20)
    private String passworld;

    @Column(length = 3)
    private Integer age;

    @Column(length = 20)
    private String birthday;

    @Column(length = 50)
    private String hobby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passworld='" + passworld + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
