package club.koumakan.framework.base.userrole;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Long>, FrameworkDAOApi {

}
