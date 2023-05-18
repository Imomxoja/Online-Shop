package uz.pdp.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import uz.pdp.domain.entity.order.OrderStatus;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderCreateDto {
     UUID userId;
     UUID productId;
     OrderStatus status;
     String productName;
     Integer amount;
     Double totalPrice;
}
