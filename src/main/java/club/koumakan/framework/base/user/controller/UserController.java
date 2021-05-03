package club.koumakan.framework.base.user.controller;

import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.base.user.service.UserService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base/user")
public class UserController extends FrameworkControllerApiImpl<User> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping("/setRole")
    public MsgResult<Void> setRole(long userId, @RequestBody List<Long> roleIds) {
        userService.setRole(userId, roleIds);
        return MsgResult.success();
    }

    @PostMapping("/login")
    public MsgResult<User> login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();

        token.setUsername(user.getUsername());
        token.setPassword(userService.crypt(user.getPassword()).toCharArray());

        try {
            subject.login(token);
            return MsgResult.success((User) subject.getPrincipal());
        } catch (LockedAccountException e) {
            return MsgResult.error("账户被锁定");
        } catch (UnknownAccountException e) {
            return MsgResult.error("用户名不存在");
        } catch (AuthenticationException e) {
            return MsgResult.error("认证失败");
        }
    }

    @GetMapping("/logout")
    public MsgResult<Void> logout() {
        SecurityUtils.getSubject().logout();
        return MsgResult.success();
    }
}
