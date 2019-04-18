package com.xxx.user.service.impl;

import com.xxx.common.model.Page;
import com.xxx.common.utils.MD5Utils;
import com.xxx.common.utils.UUIDStr;
import com.xxx.user.dao.IUserMapper;
import com.xxx.user.dto.UserDto;
import com.xxx.user.model.User;
import com.xxx.user.model.UserRole;
import com.xxx.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private IUserMapper userMapper;

    @Override
    public List<User> queryUser(String id) {
        return this.userMapper.queryUser(id);
    }

//    @Override
//    public List<User> userPage(Page page) {
//        return this.userMapper.userPage(page);
//    }

    @Override
    public List<User> userPage(UserDto userDto, Page page) {
        Map<String, String> param = new HashMap<>();
        param.put("pageIndex", String.valueOf(page.getPageIndex()));
        param.put("pageSize", String.valueOf(page.getPageSize()));
        param.put("sortField", page.getSortField());
        param.put("sortOrder", page.getSortOrder());
        param.put("userName", userDto.getUserName());

        return this.userMapper.userPage(param);
    }

    @Override
    public boolean addAccount(UserDto userDto) {
        try{
            //1. 插入用户表
            String userId = UUIDStr.getUUID();
            userDto.setUserId(userId);
            userDto.setPassword(MD5Utils.md5(userDto.getPassword()));
            this.userMapper.addAccount(userDto);
            //2.查询角色id
            String roleId = this.userMapper.findRoleIdByName(userDto.getRoleName());
            //3. 插入用户角色关联表
            UserRole userRole = new UserRole();
            userRole.setId(UUIDStr.getUUID());
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            this.userMapper.insertUserRole(userRole);
            return true;
        }catch (Exception e){
            logger.error("新增用户失败", e);
            return false;
        }

    }

    /**
     * @Author ZJC
     * @Description  编辑用户
     * @Date 10:11 2019/4/17
     * @Param [userDto]
     * @return boolean
     **/
    @Override
    public boolean editAccount(Map map) {
        try{
            UserDto userDto = new UserDto();
            userDto.setUserId((String) map.get("USERID"));
            userDto.setPassword((String) map.get("PASSWORD"));
            userDto.setRoleName((String) map.get("ROLENAME"));
            userDto.setStatus((String) map.get("STATUS"));
            userDto.setUserName((String) map.get("USERNAME"));
            //1. 更新用户信息表
            userDto.setPassword(MD5Utils.md5(userDto.getPassword()));
            this.userMapper.updateUserById(userDto);
            //2. 根据角色名查询角色id
            String roleId = this.userMapper.findRoleIdByName(userDto.getRoleName());
            //3. 更新用户角色表
            UserRole userRole = new UserRole();
            userRole.setUserId(userDto.getUserId());
            userRole.setRoleId(roleId);
            this.userMapper.updateUserRoleByUserId(userRole);
            return true;
        }catch (Exception e){
            logger.error("编辑用户失败", e);
            return false;
        }
    }

    /**
     * @Author ZJC
     * @Description 停用账号
     * @Date 10:57 2019/4/17
     * @Param [ids]
     * @return boolean
     **/
    @Override
    public boolean disabledAccount(String ids) {
        try{
            String[] idStr = ids.split(",");
            for(String id : idStr){
                this.userMapper.disabledAccount(id);
            }
            return true;
        }catch (Exception e){
            logger.error("停用账号失败", e);
            return false;
        }
    }

    /**
     * @Author ZJC
     * @Description  登录名是否存在
     * @Date 13:50 2019/4/17
     * @Param [loginName]
     * @return boolean
     **/
    @Override
    public boolean checkAccount(String loginName) {
        String name = this.userMapper.findUserByLoginName(loginName);
        if(null != name){
            return false;
        }
        return true;
    }

}
