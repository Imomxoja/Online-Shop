package uz.pdp.domain.entity.order;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.domain.BaseEntity;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.user.User;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String productName;
    private Integer amount;
    private Double totalPrice;
}
