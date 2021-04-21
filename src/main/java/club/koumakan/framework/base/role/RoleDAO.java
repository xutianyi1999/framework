package club.koumakan.framework.base.role;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long>, FrameworkDAOApi {
}
