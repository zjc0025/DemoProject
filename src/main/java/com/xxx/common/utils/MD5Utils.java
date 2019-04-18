package com.xxx.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @ClassName MD5Utils
 * @Description
 * @Author ZJC
 * @Date 2019/4/15 11:25
 */
public class MD5Utils {

    //进行shiro加密，返回加密后的结果
    public static String md5(String pass){
        String saltSource = "blog";
        String hashAlgorithmName = "MD5";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
        String password = result.toString();
        return password;
    }

    public static void main(String[] args) {
        String saltSource = "blog";
        String hashAlgorithmName = "MD5";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        String pass = "123";
        Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
        String password = result.toString();
        System.out.println(password);
    }

}
