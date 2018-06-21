package sysUser.dao;

import sysUser.entity.SysUser;

public interface SysUserMapper {
    SysUser findUserByUserCode(String userCode);
}
