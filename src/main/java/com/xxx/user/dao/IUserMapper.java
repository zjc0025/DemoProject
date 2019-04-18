package com.xxx.user.dao;

import com.xxx.user.dto.UserDto;
import com.xxx.user.model.User;
import com.xxx.user.model.UserRole;

import java.util.List;
import java.util.Map;

public interface IUserMapper {

    List<User> queryUser(String id);

//    List<User> userPage(Page page);
    List<User> userPage(Map param);

    User findUserByUsername(String username);

    List<String> findPermissionByLoginName(String loginName);

    boolean addAccount(UserDto userDto);

    String findRoleIdByName(String roleName);

    void insertUserRole(UserRole userRole);

    void updateUserById(UserDto userDto);

    void updateUserRoleByUserId(UserRole userRole);

    void disabledAccount(String id);

    String findUserByLoginName(String loginName);
}
