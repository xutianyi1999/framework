package club.koumakan.framework.base.role.entity;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "base_role")
public class Role implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 编号
    private String code;
    // 名称
    private String name;
    // 备注
    private String description;
}
