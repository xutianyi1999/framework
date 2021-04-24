package club.koumakan.framework.base.apiconfig;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiConfigDAO extends JpaRepository<ApiConfig, Long>, FrameworkDAOApi {
}
