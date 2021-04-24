package club.koumakan.framework.base.apiconfig;

import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/apiConfig")
public class ApiConfigController extends FrameworkControllerApiImpl<ApiConfig> {

    public ApiConfigController(FrameworkServiceApi<ApiConfig> service) {
        super(service);
    }
}
