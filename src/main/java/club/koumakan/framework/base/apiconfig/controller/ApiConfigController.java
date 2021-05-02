package club.koumakan.framework.base.apiconfig.controller;

import club.koumakan.framework.base.apiconfig.entity.ApiConfig;
import club.koumakan.framework.base.apiconfig.entity.ModuleNode;
import club.koumakan.framework.base.apiconfig.service.ApiConfigService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/base/apiConfig")
public class ApiConfigController extends FrameworkControllerApiImpl<ApiConfig> {

    private final ApiConfigService apiConfigService;

    public ApiConfigController(ApiConfigService apiConfigService) {
        super(apiConfigService);
        this.apiConfigService = apiConfigService;
    }

    @GetMapping("/findAllNodeList")
    public MsgResult<List<ModuleNode>> findAllNodeList() {
        ApiConfig condition = new ApiConfig();
        condition.setRoot(true);
        List<ApiConfig> apiConfigList = apiConfigService.findByCondition(condition);

        ArrayList<ModuleNode> moduleNodeList = new ArrayList<>(apiConfigList.size());

        for (ApiConfig apiConfig : apiConfigList) {
            ModuleNode node = new ModuleNode();
            toTree(apiConfig, node);
            moduleNodeList.add(node);
        }
        return MsgResult.success(moduleNodeList);
    }

    private void toTree(ApiConfig apiConfig, ModuleNode moduleNode) {
        moduleNode.setValue(apiConfig);

        ApiConfig condition = new ApiConfig();
        condition.setParentId(apiConfig.getId());
        List<ApiConfig> apiConfigList = apiConfigService.findByCondition(condition, true);
        apiConfigList.sort(Comparator.comparingInt(ApiConfig::getOrder));

        ArrayList<ModuleNode> childList = new ArrayList<>(apiConfigList.size());

        for (ApiConfig config : apiConfigList) {
            ModuleNode child = new ModuleNode();
            toTree(config, child);
            childList.add(child);
        }
        moduleNode.setChildList(childList);
    }
}
