package uz.pdp.service.order;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.dao.order.OrderDao;
import uz.pdp.domain.dto.order.OrderCreateDto;
import uz.pdp.domain.dto.order.OrderReadDto;
import uz.pdp.domain.dto.product.ProductReadDto;
import uz.pdp.domain.dto.user.UserReadDto;
import uz.pdp.domain.entity.order.Order;
import uz.pdp.domain.entity.order.OrderStatus;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.user.User;
import uz.pdp.service.product.ProductService;
import uz.pdp.service.user.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper mapper;
    @Override
    public OrderReadDto create(OrderCreateDto orderCreateDto) {
        Order map = mapper.map(orderCreateDto, Order.class);
        UserReadDto byId = userService.getById(orderCreateDto.getUserId());
        ProductReadDto byId1 = productService.getById(orderCreateDto.getProductId());

        map.setUser(mapper.map(byId, User.class));
        map.setProduct(mapper.map(byId1, Product.class));

        Order order = orderDao.create(map);

        if (order != null){
           return mapper.map(order, OrderReadDto.class);
        }
        return null;
    }

    @Override
    public OrderReadDto getById(UUID id) {
        Order order = orderDao.findById(id).get();
        return mapper.map(order, OrderReadDto.class);
    }

    @Override
    public int delete(UUID id) {
        try {
            return orderDao.deleteById(id);
        } catch (NullPointerException | NoResultException e) {
            e.getMessage();
        }
       return -1;
    }

    @Override
    public List<OrderReadDto> getAll() {
        List<Order> all = orderDao.getAll();
        List<OrderReadDto> orders = new ArrayList<>();

        for (Order order : all) {
            orders.add(mapper.map(order, OrderReadDto.class));
        }
        return orders;
    }

    @Override
    public List<OrderReadDto> getOrdersForMyProducts(UUID id) {
        List<OrderReadDto> orders = new ArrayList<>();
        List<Order> ordersForMyProducts = orderDao.getOrdersForMyProducts(id);

        for (Order ordersForMyProduct : ordersForMyProducts) {
            orders.add(mapper.map(ordersForMyProduct, OrderReadDto.class));
        }

        return orders;
    }

    @Override
    public void setStatus(UUID id, String status) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.name().equals(status)){
                orderDao.setStatus(id, value);
            }
        }
    }

    @Override
    public void setOrderAmount(UUID id, Integer amount) {
        orderDao.updateOrderAmount(id, amount);
    }

    @Override
    public List<OrderReadDto> getCustomerOrders(UUID id1) {
        List<Order> customerOrders = orderDao.getCustomerOrders(id1);
        List<OrderReadDto> orders = new ArrayList<>();

        for (Order customerOrder : customerOrders) {
            orders.add(mapper.map(customerOrder, OrderReadDto.class));
        }
        return orders;
    }
}
