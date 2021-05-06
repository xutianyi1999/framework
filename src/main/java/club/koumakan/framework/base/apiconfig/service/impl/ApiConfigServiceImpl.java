package club.koumakan.framework.base.apiconfig.service.impl;

import club.koumakan.framework.base.apiconfig.dao.ApiConfigDAO;
import club.koumakan.framework.base.apiconfig.entity.ApiConfig;
import club.koumakan.framework.base.apiconfig.service.ApiConfigService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class ApiConfigServiceImpl extends FrameworkServiceApiImpl<ApiConfig> implements ApiConfigService {

    public ApiConfigServiceImpl(ApiConfigDAO apiConfigDAO) {
        super(apiConfigDAO);
    }

    @Override
    public void translate(ApiConfig entity) {

    }
}
