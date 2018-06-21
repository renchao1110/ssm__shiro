package sysUser.service.impl;

import org.springframework.stereotype.Service;
import sysUser.dao.SysPermissionMapper;
import sysUser.dao.SysUserMapper;
import sysUser.entity.SysPermission;
import sysUser.entity.SysUser;
import sysUser.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public SysUser findUserByCode(String userCode) {
        return userMapper.findUserByUserCode(userCode);
    }

    @Override
    public List<SysPermission> findMenuListByUserId(String userid) {
        return sysPermissionMapper.findMenuListByUserId(userid);
    }

    @Override
    public List<SysPermission> findPermissionListByUserId(String userid) {
        return sysPermissionMapper.findPermissionListByUserId(userid);
    }
}
