package uz.pdp.dao.history;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.entity.history.History;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HistoryDaoImpl implements HistoryDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public History create(History history) {
        manager.persist(history);
        return history;
    }

    @Override
    public List<History> getAll() {
        return manager.createQuery("select h from histories h", History.class).getResultList();
    }

    @Override
    public History update(History history) {
        History history1 = manager.find(History.class, history.getId());
        history1.setProduct(history.getProduct());
        history1.setUser(history.getUser());
        history1.setAmount(history.getAmount());
        history1.setTotalPrice(history.getTotalPrice());
        history1.setProductName(history.getProductName());

        return manager.merge(history1);
    }

    @Override
    public Optional<History> findById(UUID uuid) {
        return Optional.of(manager.find(History.class, uuid));
    }

    @Override
    public int deleteById(UUID uuid) {
        return manager.createQuery("delete from histories where id = :id")
                .setParameter("id", uuid)
                .executeUpdate();
    }

    @Override
    public List<History> getUserHistories(UUID id) {
        return manager.createQuery("select h from histories h where h.user.id = :id", History.class)
                .setParameter("id", id).getResultList();
    }
}
