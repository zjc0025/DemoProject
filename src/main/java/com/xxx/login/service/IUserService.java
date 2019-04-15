package com.xxx.login.service;

import com.xxx.login.model.User;

import java.util.List;

public interface IUserService {

    List<User> queryUser(long id);

    List<User> userPage();
}
