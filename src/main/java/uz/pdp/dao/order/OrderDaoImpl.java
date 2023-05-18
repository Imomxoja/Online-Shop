package uz.pdp.dao.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.entity.order.Order;
import uz.pdp.domain.entity.order.OrderStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Order create(Order order) {
        Order order1 = null;
        try {
            order1 = manager.createQuery("select o from orders o where" +
                            " o.user.id = :userId and o.product.id = :productId", Order.class)
                    .setParameter("userId", order.getUser().getId())
                    .setParameter("productId", order.getProduct().getId())
                    .getSingleResult();
        } catch (NoResultException | NullPointerException e) {
            e.getMessage();
        }

        if (order1 == null) {
            manager.persist(order);
            return order;
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return manager.createQuery("select o from orders o", Order.class).getResultList();
    }

    @Override
    public Order update(Order order) {
        Order order1 = manager.find(Order.class, order.getId());
        order1.setAmount(order.getAmount());
        order1.setStatus(order.getStatus());
        order1.setProductName(order.getProductName());
        order1.setTotalPrice(order.getTotalPrice());
        return manager.merge(order1);
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        return Optional.of(manager.find(Order.class, uuid));
    }

    @Override
    @Transactional
    public int deleteById(UUID uuid) {
        return manager.createQuery("delete from orders where id = :id")
                .setParameter("id", uuid)
                .executeUpdate();
    }

    @Override
    public List<Order> getOrdersForMyProducts(UUID id) {
        return manager.createQuery("select o" +
                        " from orders o inner join products p on " +
                        "p.id = o.product.id where p.user.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional
    public void setStatus(UUID id, OrderStatus status) {
        Order order = manager.find(Order.class, id);
        order.setStatus(status);
        manager.merge(order);
    }

    @Override
    @Transactional
    public void updateOrderAmount(UUID id, Integer newAmount) {
        Order order = manager.find(Order.class, id);

        if (newAmount < 0) {
            if (order.getAmount() + newAmount > 0) {
                order.setAmount(order.getAmount() + newAmount);
            }
            if (order.getAmount() + newAmount == 0) {
                deleteById(id);
            }
        } else if (newAmount > 0) {
            order.setAmount(order.getAmount() + newAmount);
        } else {
            deleteById(id);
        }

        manager.merge(order);
    }

    @Override
    public List<Order> getCustomerOrders(UUID id1) {
        return manager.createQuery("select o from orders o where o.user.id = :id", Order.class)
                .setParameter("id", id1).getResultList();
    }
}
