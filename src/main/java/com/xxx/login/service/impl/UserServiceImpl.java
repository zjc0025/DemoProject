package com.xxx.login.service.impl;

import com.xxx.login.dao.IUserDao;
import com.xxx.login.model.User;
import com.xxx.login.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public List<User> queryUser(long id) {
        return this.userDao.queryUser(id);
    }

    @Override
    public List<User> userPage() {
        return this.userDao.userPage();
    }
}
