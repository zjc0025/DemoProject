package com.xxx.user.service;

import com.xxx.common.model.Page;
import com.xxx.user.dto.UserDto;
import com.xxx.user.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<User> queryUser(String id);

//    List<User> userPage(Page page);
    List<User> userPage(UserDto userDto, Page page);

    boolean addAccount(UserDto userDto);

    boolean editAccount(Map userDto);

    boolean disabledAccount(String ids);

    boolean checkAccount(String loginName);
}
