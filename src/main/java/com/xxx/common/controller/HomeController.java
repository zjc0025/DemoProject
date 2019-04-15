package com.xxx.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/login")
    public ModelAndView login(){

        return new ModelAndView("login");
    }

    @RequestMapping("/home")
    public ModelAndView home(){

        return new ModelAndView("home");
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(){

        return new ModelAndView("welcome/welcome");
    }

    @RequestMapping("/accountList")
    public ModelAndView accountList(){

        return new ModelAndView("account/list");
    }

}
