package club.koumakan.framework.base.role;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "base_role")
public class Role implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private Date createTime;

    private String description;
}
