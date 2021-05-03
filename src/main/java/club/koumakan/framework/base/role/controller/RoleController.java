package club.koumakan.framework.base.role.controller;

import club.koumakan.framework.base.role.entity.Role;
import club.koumakan.framework.base.role.service.RoleService;
import club.koumakan.framework.base.userrole.entity.UserRole;
import club.koumakan.framework.base.userrole.service.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/base/role")
public class RoleController extends FrameworkControllerApiImpl<Role> {

    private final UserRoleService userRoleService;

    public RoleController(RoleService roleService, UserRoleService userRoleService) {
        super(roleService);
        this.userRoleService = userRoleService;
    }

    @Override
    public MsgResult<Role> save(@RequestBody Role entity) {
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
        }
        return super.save(entity);
    }

    @Override
    public MsgResult<Void> deleteById(long id) {
        UserRole userRoleCondition = new UserRole();
        userRoleCondition.setRoleId(id);

        if (!userRoleService.exists(userRoleCondition)) {
            return super.deleteById(id);
        } else {
            return MsgResult.error("存在该角色用户");
        }
    }
}
