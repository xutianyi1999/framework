package club.koumakan.framework.base.user.controller;

import club.koumakan.framework.base.user.dao.UserDAO;
import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import club.koumakan.framework.common.http.PageRequestInfo;
import cn.hutool.crypto.digest.HMac;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/base/user")
public class UserController extends FrameworkControllerApiImpl<User, UserDAO> {

    private static final byte[] KEY = "k43bsadf8".getBytes(StandardCharsets.UTF_8);
    private static final String ALGORITHM = "HmacSHA256";

    public UserController(FrameworkServiceApiImpl<User, UserDAO> service) {
        super(service);
    }

    @Override
    @GetMapping("/findById")
    public MsgResult<User> findById(long id) {
        return super.findById(id);
    }

    @Override
    @GetMapping("/findByIds")
    public MsgResult<List<User>> findByIds(List<Long> ids) {
        return super.findByIds(ids);
    }

    @Override
    @PostMapping("/save")
    public MsgResult<User> save(@RequestBody User entity) {
        if (entity.getId() == null) {
            HMac hmac = new HMac(ALGORITHM, KEY);
            byte[] password = hmac.digest(entity.getPasswordStr(), StandardCharsets.UTF_8.name());

            entity.setPassword(password);
            entity.setCreateTime(new Date());
        } else {
            entity.setUpdateTime(new Date());
        }
        return super.save(entity);
    }

    @Override
    @PostMapping("/deleteById")
    public MsgResult<User> deleteById(@RequestBody long id) {
        return super.deleteById(id);
    }

    @Override
    @PostMapping("/deleteByIds")
    public MsgResult<User> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
    }

    @Override
    @PostMapping("/deleteByCondition")
    public MsgResult<User> deleteByCondition(@RequestBody User condition) {
        return super.deleteByCondition(condition);
    }

    @Override
    @GetMapping("/findAll")
    public MsgResult<List<User>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/findByCondition")
    public MsgResult<List<User>> findByCondition(User condition) {
        return super.findByCondition(condition);
    }

    @Override
    @GetMapping("/findByPage")
    public MsgResult<Page<User>> findByPage(PageRequestInfo pageRequestInfo, User condition) {
        return super.findByPage(pageRequestInfo, condition);
    }
}
