package uz.pdp.dao.user;

import org.springframework.stereotype.Repository;
import uz.pdp.dao.BaseDao;
import uz.pdp.domain.entity.user.User;
import java.util.UUID;

@Repository
public interface UserDao extends BaseDao<User, UUID> {
    User checkUser(User user);


}
