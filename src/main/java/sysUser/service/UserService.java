package sysUser.service;

import sysUser.entity.SysPermission;
import sysUser.entity.SysUser;

import java.util.List;

public interface UserService {
    SysUser findUserByCode(String userCode);
    //根据用户id查询权限范围的菜单
    List<SysPermission> findMenuListByUserId(String userid);

    //根据用户id查询权限范围的url
    List<SysPermission> findPermissionListByUserId(String userid);
}
