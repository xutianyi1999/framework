package club.koumakan.framework.base.userrole;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends FrameworkServiceApiImpl<UserRole> {

    public UserRoleService(JpaRepository<UserRole, Long> dao) {
        super(dao);
    }

    @Override
    public void translate(UserRole entity) {
    }
}
