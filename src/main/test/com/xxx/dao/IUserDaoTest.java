package com.xxx.dao;

import com.xxx.login.dao.IUserDao;
import com.xxx.login.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void queryUser() {

        long id = 1;
        List<User> res = userDao.queryUser(id);
        System.out.println(res.get(0).toString());

    }
}