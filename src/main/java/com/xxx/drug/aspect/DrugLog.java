package com.xxx.drug.aspect;

import java.lang.annotation.*;

/**
 * @ClassName DrugLog
 * @Description
 * @Author ZJC
 * @Date 2019/4/18 16:34
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DrugLog {

    String module()  default "";
    String methods()  default "";

}
