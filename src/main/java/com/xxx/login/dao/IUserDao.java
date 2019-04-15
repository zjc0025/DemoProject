package com.xxx.login.dao;

import com.xxx.login.model.User;

import java.util.List;

public interface IUserDao {

    List<User> queryUser(long id);

    List<User> userPage();

    User findUserByUsername(String username);
}
