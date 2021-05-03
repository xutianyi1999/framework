package club.koumakan.framework.base.role.service.impl;

import club.koumakan.framework.base.role.dao.RoleDAO;
import club.koumakan.framework.base.role.entity.Role;
import club.koumakan.framework.base.role.service.RoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends FrameworkServiceApiImpl<Role> implements RoleService {

    public RoleServiceImpl(RoleDAO roleDAO) {
        super(roleDAO);
    }

    @Override
    public void translate(Role entity) {

    }
}
