package club.koumakan.framework.common.datastructure;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode<V> {

    private V value;

    private List<TreeNode<V>> childList;
}
