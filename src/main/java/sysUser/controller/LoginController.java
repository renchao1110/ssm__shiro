package sysUser.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String exceptionClassName = request.getParameter("shiroLoginFailure");
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)){
                throw new RuntimeException("用户账号不存在！！！！");
            }else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                throw new RuntimeException("账户或密码错误");
            }else {
                throw new RuntimeException("出现其他错误登录失败");
            }
        }
        return "login";
    }
}
