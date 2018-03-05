package com.example.entry;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 9:52
 * @Version 1.0
 */
@Table
@Entity(name = "u_user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nickname")
    private String nickName;

    @Column(name="email")
    private String email;

    @Column(name="pswd")
    private String pswd;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @Column(name="status")
    private Integer status;

    /**
     * 角色(一个用户具有多个角色)
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roleList;

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    public User(String nickName, String email, String pswd, Date createTime, Date lastLoginTime, Integer status) {
        this.nickName = nickName;
        this.email = email;
        this.pswd = pswd;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
