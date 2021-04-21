package club.koumakan.framework.base.userrole;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends FrameworkServiceApiImpl<UserRole, UserRoleDAO> {

    public UserRoleService(UserRoleDAO userRoleDAO) {
        super(userRoleDAO);
    }

    @Override
    public void translate(UserRole entity) {

    }
}
