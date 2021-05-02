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

    private String code;

    private String name;

    private Long parentId;

    private Boolean root;

    private Integer order;

    private String description;
}
