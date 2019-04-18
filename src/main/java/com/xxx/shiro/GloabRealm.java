package com.xxx.shiro;

import com.xxx.user.dao.IUserMapper;
import com.xxx.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义realm
 *
 * @author pengwei
 *
 */
public class GloabRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(GloabRealm.class);

    @Resource
    private IUserMapper userMapper;

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("-------授权方法执行----------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 根据当前登录用户查询数据库权限，为用户授权，这是两种获取当前用户的方法

        //从 principals获取主身份信息
        User user2 = (User) principals.getPrimaryPrincipal();
        List<String> permissions = userMapper.findPermissionByLoginName(user2.getLoginName());
        if(permissions != null){
            for(String str : permissions){
                info.addStringPermission(str);
            }
        }

        return info;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("------自定义realm执行了认证方法......");
        UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
        if (StringUtils.isNotBlank(mytoken.getUsername())) {
            // 1,取出用户名
            String username = mytoken.getUsername();
            // 2,去数据库中查询用户信息根据用户名
            User user = userMapper.findUserByUsername(username);
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