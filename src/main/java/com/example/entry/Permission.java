package com.example.entry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/2 11:06
 * @Version 1.0
 */
@Entity
@Table(name = "u_permission")
public class Permission implements Serializable{

    @Id
    @Column(name= "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    /**
     * 权限--角色(多对多)
     */
    @ManyToMany
    @JoinTable(name = "RolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
