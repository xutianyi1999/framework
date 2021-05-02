package club.koumakan.framework.base.user.controller;

import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.base.user.service.impl.UserServiceImpl;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import cn.hutool.crypto.digest.HMac;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/base/user")
public class UserController extends FrameworkControllerApiImpl<User> {

    private static final String ALGORITHM = "HmacSHA256";

    private final UserServiceImpl userServiceImpl;
    private final byte[] key;

    public UserController(
            UserServiceImpl userServiceImpl,
            @Value("${framework.user.signKey}") String signKey
    ) {
        super(userServiceImpl);
        this.userServiceImpl = userServiceImpl;
        this.key = signKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public MsgResult<User> save(@RequestBody User entity) {
        if (Strings.isNotBlank(entity.getPassword())) {
            entity.setPassword(crypt(entity.getPassword()));
        }

        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
        } else {
            entity.setUpdateTime(new Date());
        }
        return super.save(entity);
    }

    @PostMapping("/setRole")
    public MsgResult<Void> setRole(long userId, @RequestBody List<Long> roleIds) {
        userServiceImpl.setRole(userId, roleIds);
        return MsgResult.success();
    }

    @PostMapping("/login")
    public MsgResult<User> login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();

        token.setUsername(user.getUsername());
        token.setPassword(crypt(user.getPassword()).toCharArray());

        try {
            subject.login(token);
            return MsgResult.success((User) subject.getPrincipal());
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

    private String crypt(String password) {
        HMac hmac = new HMac(ALGORITHM, key);
        return hmac.digestHex(password, StandardCharsets.UTF_8.name());
    }
}
