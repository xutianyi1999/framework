package club.koumakan.framework.base.userrole.service.impl;

import club.koumakan.framework.base.userrole.dao.UserRoleDAO;
import club.koumakan.framework.base.userrole.entity.UserRole;
import club.koumakan.framework.base.userrole.service.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends FrameworkServiceApiImpl<UserRole> implements UserRoleService {

    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        super(userRoleDAO);
    }

    @Override
    public void translate(UserRole entity) {
    }
}
