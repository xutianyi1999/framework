package club.koumakan.framework.base.apiconfig.entity;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "base_api_config")
public class ApiConfig implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 编号
    private String code;
    // 名称
    private String name;
    // 父节点
    private Long parentId;
    // 是否是ROOT节点
    private Boolean root;
    // 序号
    private Integer order;
    // 备注
    private String description;
}
