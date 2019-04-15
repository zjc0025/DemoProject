//package com.xxx.common.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @Author ZJC
// * @Description
// * @Date 16:05 2019/4/12
// * @Param
// * @return
// **/
//public class TestInterceptor implements HandlerInterceptor {
//
//    private final static Logger logger = LoggerFactory.getLogger(TestInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        logger.info("==============执行顺序: 1、preHandle================");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        logger.info("==============执行顺序: 2、postHandle================");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        logger.info("==============执行顺序: 3、afterCompletion================");
//    }
//}
