package com.xxx.service.impl;

import com.xxx.dao.IUserDao;
import com.xxx.model.User;
import com.xxx.service.IUserService;
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
