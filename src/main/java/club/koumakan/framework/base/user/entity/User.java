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

    private String name;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Date createTime;

    private Date updateTime;

    @Transient
    private List<Role> roles;
}
