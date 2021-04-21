package club.koumakan.framework.base.user;

import club.koumakan.framework.base.role.Role;
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
    private byte[] password;

    private Date createTime;

    private Date updateTime;

    @Transient
    private String passwordStr;

    @Transient
    private List<Role> roles;
}
