package sysUser.dao;

import sysUser.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    //根据用户id查询菜单
    List<SysPermission> findMenuListByUserId(String userid);
    //根据用户id查询权限url
    List<SysPermission> findPermissionListByUserId(String userid);
}
