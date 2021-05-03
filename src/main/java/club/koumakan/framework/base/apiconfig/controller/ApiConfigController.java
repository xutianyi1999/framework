package club.koumakan.framework.base.apiconfig.controller;

import club.koumakan.framework.base.apiconfig.entity.ApiConfig;
import club.koumakan.framework.base.apiconfig.service.ApiConfigService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.datastructure.TreeNode;
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
    public MsgResult<List<TreeNode<ApiConfig>>> findAllNodeList() {
        ApiConfig condition = new ApiConfig();
        condition.setRoot(true);
        List<ApiConfig> apiConfigList = apiConfigService.findByCondition(condition);

        ArrayList<TreeNode<ApiConfig>> treeNodeList = new ArrayList<>(apiConfigList.size());

        for (ApiConfig apiConfig : apiConfigList) {
            TreeNode<ApiConfig> node = new TreeNode<>();
            toTree(apiConfig, node);
            treeNodeList.add(node);
        }
        return MsgResult.success(treeNodeList);
    }

    private void toTree(ApiConfig apiConfig, TreeNode<ApiConfig> treeNode) {
        treeNode.setValue(apiConfig);

        ApiConfig condition = new ApiConfig();
        condition.setParentId(apiConfig.getId());
        List<ApiConfig> apiConfigList = apiConfigService.findByCondition(condition, true);
        apiConfigList.sort(Comparator.comparingInt(ApiConfig::getOrder));

        ArrayList<TreeNode<ApiConfig>> childList = new ArrayList<>(apiConfigList.size());

        for (ApiConfig config : apiConfigList) {
            TreeNode<ApiConfig> child = new TreeNode<>();
            toTree(config, child);
            childList.add(child);
        }
        treeNode.setChildList(childList);
    }
}
