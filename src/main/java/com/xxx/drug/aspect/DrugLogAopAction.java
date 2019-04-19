package com.xxx.drug.aspect;

import com.xxx.common.service.ILogService;
import com.xxx.drug.model.Drug;
import com.xxx.drug.model.LogEntity;
import com.xxx.user.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DrugLogAopAction
 * @Description
 * @Author ZJC
 * @Date 2019/4/18 16:35
 */
@Aspect
@Component
public class DrugLogAopAction {

    //注入service,用来将日志信息保存在数据库
//    @Resource(name="logService")
//    private LogServiceImpl logservice;

    @Resource
    private ILogService logService;

    private final static Logger logger = LoggerFactory.getLogger(DrugLogAopAction.class);

    @Around("execution(* com.xxx.drug.controller.DrugController.*putDrug(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //常见日志实体对象
        LogEntity log = new LogEntity();
        //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        log.setLoginName(loginUser.getLoginName());
        //获取系统时间
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        log.setUpdateDate(time);

        //获取系统ip
        String ip = getRemortIP(request);
        log.setIp(ip);

        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        Drug drug = (Drug) args[0];
        log.setDrugId(drug.getId());
        if("inputDrug".equals(methodName)){
            log.setDescribe("入库数量：" + drug.getStock());
        }else if("outputDrug".equals(methodName)){
            log.setDescribe("出库数量：" + drug.getStock());
        }
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            logger.error("获取请求方法失败", e);
        }
        if (null != method) {
            // 判断是否包含自定义的注解，@DrugLog
            if (method.isAnnotationPresent(DrugLog.class)) {
                DrugLog drugLog = method.getAnnotation(DrugLog.class);
                log.setModule(drugLog.module());
                log.setMethod(drugLog.methods());
                try {
                    object = pjp.proceed();
                    long end = System.currentTimeMillis();
                    //将计算好的时间保存在实体中
                    log.setResponseTime(""+(end-start));
                    log.setResult("执行成功！");
                    System.out.println(log);
                    //保存进数据库
                    logService.saveLog(log);
                } catch (Throwable e) {
                    logger.error(drugLog.methods() + "方法执行失败", e);
                    long end = System.currentTimeMillis();
                    log.setResponseTime(""+(end-start));
                    log.setResult("执行失败！");
                    System.out.println(log);
                    logService.saveLog(log);
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
