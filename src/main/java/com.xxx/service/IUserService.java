package com.xxx.service;

import com.xxx.model.User;

import java.util.List;

public interface IUserService {

    List<User> queryUser(long id);

    List<User> userPage();
}
