package com.xxx.common.utils;

import java.util.UUID;

/**
 * @ClassName UUIDStr
 * @Description
 * @Author ZJC
 * @Date 2019/4/16 16:04
 */
public class UUIDStr {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);
    }

}
