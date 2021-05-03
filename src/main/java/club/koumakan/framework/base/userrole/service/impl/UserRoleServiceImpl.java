package club.koumakan.framework.base.userrole.service.impl;

import club.koumakan.framework.base.userrole.entity.UserRole;
import club.koumakan.framework.base.userrole.service.UserRoleService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends FrameworkServiceApiImpl<UserRole> implements UserRoleService {

    public UserRoleServiceImpl(JpaRepository<UserRole, Long> dao) {
        super(dao);
    }

    @Override
    public void translate(UserRole entity) {
    }
}
