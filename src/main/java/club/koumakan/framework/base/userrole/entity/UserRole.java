package club.koumakan.framework.base.userrole.entity;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "base_user_role")
public class UserRole implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roleId;
}
