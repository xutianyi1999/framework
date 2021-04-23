package club.koumakan.framework.base.user;

import club.koumakan.framework.base.role.Role;
import club.koumakan.framework.base.role.RoleService;
import club.koumakan.framework.base.userrole.UserRole;
import club.koumakan.framework.base.userrole.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends FrameworkServiceApiImpl<User, UserDAO> {

    private final UserRoleService userRoleService;

    private final RoleService roleService;

    public UserService(UserDAO userDAO, UserRoleService userRoleService, RoleService roleService) {
        super(userDAO);
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }

    @Transactional
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

    public User findByUsername(String username) {
        User condition = new User();
        condition.setUsername(username);

        List<User> userList = super.findByCondition(condition, true);

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        UserRole condition = new UserRole();
        condition.setUserId(id);
        userRoleService.deleteByCondition(condition);

        super.deleteById(id);
    }

    @Transactional
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void translate(User user) {
        if (user != null) {
            UserRole userRoleCondition = new UserRole();
            userRoleCondition.setUserId(user.getId());

            List<Long> roleIds = userRoleService.findByCondition(userRoleCondition).stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toList());

            List<Role> roles = roleService.findByIds(roleIds, true);
            user.setRoles(roles);
        }
    }
}
