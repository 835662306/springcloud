package com.example.controller;

import com.example.dao.UserRepostitory;
import com.example.entry.User;
import com.example.publicResult.ActionResult;
import com.example.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserId(@PathVariable(value = "id") Integer id) {
//        ModelMap modelMap = new ModelMap();
//        User user1 = userRepostitory.findOne(id);
//        modelMap.put("user", user1);
        return "home";
    }

    @GetMapping("home")
    public String myHome(ModelMap modelMap){
        modelMap.addAttribute("name", "guoxiaojing");
        return "word";
    }

    /**
     * @ApiModel 表明这是一个被swagger框架管理的model，用于class上
     * @ApiModelProperty 这里顾名思义，就是标注在被标注了@ApiModel的class的属性上，这里的value是对字段的描述，example是取值例子，
     * 注意这里的example很有用，对于前后端开发工程师理解文档起到了关键的作用，因为会在api文档页面上显示出这些取值来；这个注解还有一
     * 些字段取值，可以自己研究，举例说一个：position，表明字段在model中的顺序
     * @ApiOperation标注在具体请求上，value和notes的作用差不多，都是对请求进行说明；tags则是对请求进行分类的，比如你有好几个controller，
     * 分别属于不同的功能模块，那这里我们就可以使用tags来区分了，看上去很有条理
     * @return
     */
    @ApiOperation(value = "查询全部", notes = "查询user全部数据")
    @ResponseBody
    @GetMapping("getAll")
    public ActionResult getAll(){
        List<User> all = userService.getAll();
        ActionResult actionResult = new ActionResult();
        actionResult.put("data", all);
        return actionResult;
    }
}
