package club.koumakan.framework.base.user.dao;

import club.koumakan.framework.base.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

}
