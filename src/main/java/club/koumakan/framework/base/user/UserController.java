package club.koumakan.framework.base.user;

import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import cn.hutool.crypto.digest.HMac;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/base/user")
public class UserController extends FrameworkControllerApiImpl<User, UserDAO> {

    private static final byte[] KEY = "k43bsadf8".getBytes(StandardCharsets.UTF_8);
    private static final String ALGORITHM = "HmacSHA256";

    private final UserService userService;

    public UserController(FrameworkServiceApiImpl<User, UserDAO> service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @Override
    public MsgResult<User> save(@RequestBody User entity) {
        if (Strings.isNotBlank(entity.getPasswordStr())) {
            HMac hmac = new HMac(ALGORITHM, KEY);
            byte[] password = hmac.digest(entity.getPasswordStr(), StandardCharsets.UTF_8.name());

            entity.setPassword(password);
        }

        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
        } else {
            entity.setUpdateTime(new Date());
        }
        return super.save(entity);
    }

    @PostMapping("/setRole")
    public MsgResult<User> setRole(long userId, @RequestBody List<Long> roleIds) {
        userService.setRole(userId, roleIds);
        return MsgResult.success();
    }
}
