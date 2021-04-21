package club.koumakan.framework.base.role;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends FrameworkServiceApiImpl<Role, RoleDAO> {

    public RoleService(RoleDAO roleDAO) {
        super(roleDAO);
    }

    @Override
    public void translate(Role entity) {

    }
}
