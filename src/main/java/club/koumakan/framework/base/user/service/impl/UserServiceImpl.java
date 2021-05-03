package club.koumakan.framework.base.user.service.impl;

import club.koumakan.framework.base.role.entity.Role;
import club.koumakan.framework.base.role.service.RoleService;
import club.koumakan.framework.base.user.dao.UserDAO;
import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.base.user.service.UserService;
import club.koumakan.framework.base.userrole.entity.UserRole;
import club.koumakan.framework.base.userrole.service.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.HMac;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends FrameworkServiceApiImpl<User> implements UserService {
    private static final String ALGORITHM = "HmacSHA256";

    private final byte[] key;
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    public UserServiceImpl(
            UserDAO userDAO,
            UserRoleService userRoleService,
            RoleService roleService,
            @Value("${framework.user.signKey}") String signKey
    ) {
        super(userDAO);
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.key = signKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public User save(User user) {
        User newUser;

        if (user.getId() != null) {
            User dbUser = super.findById(user.getId());
            BeanUtil.copyProperties(user, dbUser, "username", "password", "createTime");
            newUser = dbUser;
        } else {
            user.setPassword(crypt(user.getPassword()));
            user.setCreateTime(new Date());
            newUser = user;
        }
        return super.save(newUser);
    }

    @Transactional
    @Override
    public void setRole(long userId, List<Long> roleIds) {
        UserRole userRoleCondition = new UserRole();
        userRoleCondition.setUserId(userId);
        userRoleService.deleteByCondition(userRoleCondition);

        ArrayList<UserRole> userRoles = new ArrayList<>(roleIds.size());

        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoles.add(userRole);
        }
        userRoleService.saveAll(userRoles);
    }

    @Override
    public User findByUsername(String username) {
        User condition = new User();
        condition.setUsername(username);

        return super.findOneByCondition(condition, true);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        UserRole condition = new UserRole();
        condition.setUserId(id);
        userRoleService.deleteByCondition(condition);

        super.deleteById(id);
    }

    @Override
    public void translate(User user) {
        if (user != null) {
            // 角色
            UserRole userRoleCondition = new UserRole();
            userRoleCondition.setUserId(user.getId());

            List<Long> roleIds = userRoleService.findByCondition(userRoleCondition).stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toList());

            List<Role> roles = roleService.findByIds(roleIds, true);
            user.setRoles(roles);

            // 是否锁定
            if (user.getLocked().equals(true)) {
                user.setLockedStr("已锁定");
            } else {
                user.setLockedStr("未锁定");
            }
        }
    }

    @Override
    public String crypt(String password) {
        HMac hmac = new HMac(ALGORITHM, key);
        return hmac.digestHex(password, StandardCharsets.UTF_8.name());
    }
}
