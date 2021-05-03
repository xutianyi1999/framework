package club.koumakan.framework.base.user.entity;

import club.koumakan.framework.base.role.entity.Role;
import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "base_user")
public class User implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 名称
    private String name;
    // 用户名
    private String username;
    // 密码
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 是否锁定
    private Boolean locked;
    // 创建时间
    private Date createTime;

    @Transient
    private List<Role> roles;

    @Transient
    private String lockedStr;
}
