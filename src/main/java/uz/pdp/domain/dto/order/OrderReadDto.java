package uz.pdp.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.domain.entity.order.OrderStatus;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReadDto {
    UUID id;
    UUID userId;
    UUID productId;
    OrderStatus status;
    String productName;
    Integer amount;
    Double totalPrice;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
