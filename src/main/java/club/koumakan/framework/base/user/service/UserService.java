package club.koumakan.framework.base.user.service;

import club.koumakan.framework.base.user.dao.UserDAO;
import club.koumakan.framework.base.user.entity.User;
import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends FrameworkServiceApiImpl<User, UserDAO> {

    public UserService(UserDAO userDAO) {
        super(userDAO);
    }

    @Override
    public void translate(User entity) {
    }
}
