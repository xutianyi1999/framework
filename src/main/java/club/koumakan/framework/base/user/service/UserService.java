package club.koumakan.framework.base.user.service;

import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;

import java.util.List;

public interface UserService extends FrameworkServiceApi<User> {

    void setRole(long userId, List<Long> roleIds);

    User findByUsername(String username);
}
