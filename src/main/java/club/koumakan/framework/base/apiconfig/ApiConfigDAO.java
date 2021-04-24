package club.koumakan.framework.base.apiconfig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiConfigDAO extends JpaRepository<ApiConfig, Long> {
}
