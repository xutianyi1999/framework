package club.koumakan.framework.base.apiconfig.entity;

import lombok.Data;

import java.util.List;

@Data
public class ModuleNode {

    private ApiConfig value;

    private List<ModuleNode> childList;
}
