package club.koumakan.framework.base.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

}
