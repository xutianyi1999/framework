package club.koumakan.framework.base.role;

import club.koumakan.framework.base.userrole.UserRole;
import club.koumakan.framework.base.userrole.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/base/role")
public class RoleController extends FrameworkControllerApiImpl<Role, RoleDAO> {

    private final UserRoleService userRoleService;

    public RoleController(FrameworkServiceApiImpl<Role, RoleDAO> service, UserRoleService userRoleService) {
        super(service);
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
    public MsgResult<Role> deleteById(long id) {
        UserRole userRoleCondition = new UserRole();
        userRoleCondition.setRoleId(id);

        if (userRoleService.findByCondition(userRoleCondition).isEmpty()) {
            return super.deleteById(id);
        } else {
            return MsgResult.error("存在该角色用户");
        }
    }
}
