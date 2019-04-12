package com.xxx.controller;

import com.xxx.model.User;
import com.xxx.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/query")
    @ResponseBody
    public Map getUser(@RequestBody User user){

        logger.info("查询所有用户");
        List<User> users = this.userService.queryUser(user.getId());

        Map map = new HashMap();
        map.put("status", "成功");
        map.put("data", users);

        return map;
    }

    @RequestMapping("/page")
    @ResponseBody
    public Map page(){
        logger.info("查询单个用户");
        List<User> users = this.userService.userPage();

        Map map = new HashMap();
        map.put("status", "成功");
        map.put("data", users);
        return map;
    }

}
