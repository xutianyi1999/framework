package club.koumakan.framework.base.role.dao;

import club.koumakan.framework.base.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
