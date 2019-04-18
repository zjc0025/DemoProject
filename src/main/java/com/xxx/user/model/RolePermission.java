package com.xxx.user.model;

/**
 * @ClassName RolePermission
 * @Description
 * @Author ZJC
 * @Date 2019/4/16 10:12
 */
public class RolePermission {

    private String id;

    private String permissionId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
