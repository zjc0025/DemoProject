package com.xxx.shiro;

import com.xxx.login.dao.IUserDao;
import com.xxx.login.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 自定义realm
 *
 * @author pengwei
 *
 */
public class GloabRealm extends AuthorizingRealm {

    @Resource
    private IUserDao userDao;

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权方法执行了........");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 测试为用户授权
        info.addStringPermission("staff-list");
        // TODO 自己根据当前登录用户查询数据库权限，为用户授权，这是两种获取当前用户的方法
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        User user2 = (User) principals.getPrimaryPrincipal();
        System.out.println(user1 == user2);
        return info;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("------自定义realm执行了认证方法......");
        UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
        if (StringUtils.isNotBlank(mytoken.getUsername())) {
            // 1,取出用户名
            String username = mytoken.getUsername();
            // 2,去数据库中查询用户信息根据用户名
            User user = userDao.findUserByUsername(username);
            if (user == null) {
                // 3，用户不存在，返回null框架抛出异常，到controller进行统一处理
                return null;
            }
            // 4，到这里说明数据已经查询到了，让shiro进行对页面提交的密码和数据库中的密码进行校验
            // 第一个:参数数据库查询出来的用户对象，第二个:数据库密码，第三个参数:当前Realm名字
            AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            return info;
        }
        // 用户名为空，返回null抛出异常提示用户
        return null;
    }

}