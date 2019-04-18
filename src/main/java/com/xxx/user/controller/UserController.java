package com.xxx.user.controller;

import com.xxx.common.model.Page;
import com.xxx.common.model.PageResult;
import com.xxx.common.utils.MD5Utils;
import com.xxx.user.dto.UserDto;
import com.xxx.user.model.User;
import com.xxx.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ZJC
 * @Description
 * @Date 15:18 2019/4/15
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private IUserService userService;


    // 用户登录使用shiro进行验证
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    @ResponseBody
    public String checkLogin1(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 使用shiro框架的方式进行认证
        Subject subject = SecurityUtils.getSubject();// 获取当前用户登录对象，现在状态为“未认证”
        // 用户名和密码令牌
        AuthenticationToken token = new UsernamePasswordToken(user.getUserName(), MD5Utils.md5(user.getPassword()));
//        AuthenticationToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(token);
            User loginUser = (User) subject.getPrincipal();
            session.setAttribute("loginUser", loginUser);
            // 登录成功，跳转到首页
            return "true";
        } catch (Exception e) {
            logger.error("登录失败", e);
            // 验证失败，重定向到登录页面
            return "false";
        }

    }


    @RequestMapping("/query")
    @ResponseBody
    public Map getUser(@RequestBody User user) {

        logger.info("查询所有用户");
        List<User> users = this.userService.queryUser(user.getId());

        Map map = new HashMap();
        map.put("status", "成功");
        map.put("data", users);

        return map;
    }

//    @RequestMapping("/page")
//    @ResponseBody
//    public PageResult page(Page page) {
//        List<User> users = this.userService.userPage(page);
//        page.setTotal(users.size());
//
//        PageResult pageResult = new PageResult(page, users);
//
//        return pageResult;
//    }

    @RequestMapping("/page")
    @ResponseBody
    public PageResult page( UserDto userDto, Page page) {
        List<User> users = this.userService.userPage(userDto, page);
        page.setTotal(users.size());

        PageResult pageResult = new PageResult(page, users);

        return pageResult;
    }

    @RequestMapping("/addAccount")
    @ResponseBody
    public String addAccount(@RequestBody UserDto userDto) {
        boolean f = this.userService.addAccount(userDto);

        return String.valueOf(f);
    }

    @RequestMapping("/editAccount")
    @ResponseBody
    public String editAccount(@RequestBody Map userDto) {
        boolean f = this.userService.editAccount(userDto);

        return String.valueOf(f);
    }

    @RequestMapping("/disabledAccount")
    @ResponseBody
    public String disabledAccount(@RequestBody String ids) {
        boolean f = this.userService.disabledAccount(ids);
        return String.valueOf(true);
    }

    @RequestMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(@RequestBody String loginName) {
        boolean f = this.userService.checkAccount(loginName);
        return f;
    }

}
