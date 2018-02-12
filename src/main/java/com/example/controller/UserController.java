package com.example.controller;

import com.example.dao.UserRepostitory;
import com.example.entry.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/12 10:15
 * @Version 1.0
 */
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserRepostitory userRepostitory;


    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserId(@PathVariable(value = "id") Integer id) {
        ModelMap modelMap = new ModelMap();
        User user = new User();
        user.setId(1);
        user.setAge(12);
        user.setUsername("guoxiaojing");

        User user1 = userRepostitory.findOne(id);
        modelMap.put("user", user1);
        return "this is guoxiaojing";
    }

    @RequestMapping("/users")
    public String findUserById(){
        return "this is guoxiaojing";
    }
}
