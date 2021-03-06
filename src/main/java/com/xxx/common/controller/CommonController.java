package com.xxx.common.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author ZJC
 * @Description 页面跳转控制
 * @Date 11:16 2019/4/16
 * @Param
 * @return
 **/
@Controller
public class CommonController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/home")
    public String home(){
        return "welcome/home";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome/welcome";
    }

    /**
     * @Author ZJC
     * @Description 账号管理
     * @Date 11:15 2019/4/16
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/accountList")
    @RequiresPermissions("account_manage")
    public ModelAndView accountList(){
        return new ModelAndView("account/list");
    }

    /**
     * @Author ZJC
     * @Description 账号管理增加
     * @Date 11:15 2019/4/16
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/addAccount")
    @RequiresPermissions("account_manage")
    public ModelAndView addAccount(){
        return new ModelAndView("account/add");
    }

    /**
     * @Author ZJC
     * @Description 账号管理编辑
     * @Date 11:15 2019/4/16
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/editAccount")
    @RequiresPermissions("account_manage")
    public ModelAndView editAccount(){
        return new ModelAndView("account/edit");
    }

    @RequestMapping("/drug")
    public ModelAndView drug(){
        return new ModelAndView("drug/list");
    }

    @RequestMapping("/drugCount")
    @RequiresPermissions("drug_query")
    public ModelAndView drugCount(){
        return new ModelAndView("drug/drug-count");
    }

    @RequestMapping("/drugAdd")
    @RequiresPermissions("drug_query")
    public ModelAndView drugAdd(){
        return new ModelAndView("drug/add");
    }

    @RequestMapping("/drugEdit")
    @RequiresPermissions("drug_query")
    public ModelAndView drugEdit(){
        return new ModelAndView("drug/edit");
    }

    @RequestMapping("/inputOutput")
    public ModelAndView inputOutput(){
        return new ModelAndView("drug/input-output");
    }

    @RequestMapping("/logList")
    @RequiresPermissions("drug_query")
    public ModelAndView logList(){
        return new ModelAndView("drug/log-list");
    }

    @RequestMapping("/unauthenticatedException")
    public ModelAndView unauthenticatedException(){
        return new ModelAndView("account/error");
    }

}
