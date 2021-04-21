package club.koumakan.framework.base.user;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long>, FrameworkDAOApi {

}
