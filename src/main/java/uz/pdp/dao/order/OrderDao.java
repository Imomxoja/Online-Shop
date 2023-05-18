package uz.pdp.dao.order;

import org.springframework.stereotype.Repository;
import uz.pdp.dao.BaseDao;
import uz.pdp.domain.entity.order.Order;
import uz.pdp.domain.entity.order.OrderStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDao extends BaseDao<Order, UUID> {

    List<Order> getOrdersForMyProducts(UUID id);

    void setStatus(UUID id, OrderStatus status);

    void updateOrderAmount(UUID id, Integer newAmount);

    List<Order> getCustomerOrders(UUID id1);
}
