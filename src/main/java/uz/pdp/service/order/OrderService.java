package uz.pdp.service.order;

import org.springframework.stereotype.Service;
import uz.pdp.domain.dto.order.OrderCreateDto;
import uz.pdp.domain.dto.order.OrderReadDto;
import uz.pdp.service.BaseService;
import java.util.List;
import java.util.UUID;

@Service
public interface OrderService extends BaseService<OrderCreateDto, OrderReadDto> {

    List<OrderReadDto> getOrdersForMyProducts(UUID id);

    void setStatus(UUID id, String status);

    void setOrderAmount(UUID id, Integer amount);

    List<OrderReadDto> getCustomerOrders(UUID id1);
}
