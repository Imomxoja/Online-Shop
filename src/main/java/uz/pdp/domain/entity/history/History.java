package uz.pdp.domain.entity.history;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.domain.BaseEntity;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.user.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "histories")
public class History extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String productName;
    private Integer amount;
    private Double totalPrice;
}
