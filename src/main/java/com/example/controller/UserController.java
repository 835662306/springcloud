package com.example.controller;

import com.example.dao.UserRepostitory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/12 10:15
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserRepostitory userRepostitory;


    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserId(@PathVariable(value = "id") Integer id) {
//        ModelMap modelMap = new ModelMap();
//        User user1 = userRepostitory.findOne(id);
//        modelMap.put("user", user1);
        return "this is guoxiaojing";
    }

    @GetMapping("home")
    public String myHome(ModelMap modelMap){
        modelMap.addAttribute("name", "guoxiaojing");
        return "word";
    }

}
