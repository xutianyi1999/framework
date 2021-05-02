package club.koumakan.framework.base.apiconfig.dao;

import club.koumakan.framework.base.apiconfig.entity.ApiConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiConfigDAO extends JpaRepository<ApiConfig, Long> {
}
