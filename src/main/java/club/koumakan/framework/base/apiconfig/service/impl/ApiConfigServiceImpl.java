package club.koumakan.framework.base.apiconfig.service.impl;

import club.koumakan.framework.base.apiconfig.entity.ApiConfig;
import club.koumakan.framework.base.apiconfig.service.ApiConfigService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiConfigServiceImpl extends FrameworkServiceApiImpl<ApiConfig> implements ApiConfigService {

    public ApiConfigServiceImpl(JpaRepository<ApiConfig, Long> dao) {
        super(dao);
    }

    @Override
    public void translate(ApiConfig entity) {

    }
}
