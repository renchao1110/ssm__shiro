package sysUser.MyRealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sysUser.dao.SysPermissionMapper;
import sysUser.dao.SysUserMapper;
import sysUser.entity.ActiveUser;
import sysUser.entity.SysPermission;
import sysUser.entity.SysUser;
import sysUser.service.UserService;
import sysUser.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;


    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userCode = (String)authenticationToken.getPrincipal();
        SysUser sysUser = userService.findUserByCode(userCode);
        if(sysUser==null){
            return null;
        }
        String password = sysUser.getPassword();
        String salt = sysUser.getSalt();
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsername(sysUser.getUsername());

        List<SysPermission> menus = userService.findMenuListByUserId(sysUser.getId());

        activeUser.setMenus(menus);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,password,ByteSource.Util.bytes(salt),this.getName());
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();
        List<SysPermission> permissions = userService.findPermissionListByUserId(activeUser.getUserid());
        List<String> permissionList = new ArrayList<>();
        if (permissions!=null){
            for (SysPermission sysPermission:permissions){
                permissionList.add(sysPermission.getPercode());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }
}
