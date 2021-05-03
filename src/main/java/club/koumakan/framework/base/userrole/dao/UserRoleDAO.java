package club.koumakan.framework.base.userrole.dao;

import club.koumakan.framework.base.userrole.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

}
