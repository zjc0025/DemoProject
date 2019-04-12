package com.xxx.dao;

import com.xxx.model.User;

import java.util.List;

public interface IUserDao {

    List<User> queryUser(long id);

    List<User> userPage();
}
