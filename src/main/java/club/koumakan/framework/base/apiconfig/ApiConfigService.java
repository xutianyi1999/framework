package club.koumakan.framework.base.apiconfig;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiConfigService extends FrameworkServiceApiImpl<ApiConfig> {

    public ApiConfigService(JpaRepository<ApiConfig, Long> dao) {
        super(dao);
    }

    @Override
    public void translate(ApiConfig entity) {

    }
}
