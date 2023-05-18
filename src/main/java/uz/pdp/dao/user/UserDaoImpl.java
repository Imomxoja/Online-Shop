package uz.pdp.dao.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.entity.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public User create(User user) {
        manager.persist(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return manager.createQuery("select u from users u", User.class).getResultList();
    }

    @Override
    public User update(User user) {
        User user1 = manager.find(User.class, user.getId());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setBalance(user.getBalance());
        return manager.merge(user1);
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return Optional.of(manager.find(User.class, uuid));
    }

    @Override
    public int deleteById(UUID uuid) {
        return manager.createQuery("delete from users where id = :id")
                .setParameter("id", uuid).executeUpdate();
    }

    @Override
    public User checkUser(User user) {
        return manager.createQuery("select u from users u where u.email = :email and" +
                        " u.password = :password", User.class)
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .getSingleResult();
    }
}
