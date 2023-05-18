package uz.pdp.domain.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import uz.pdp.domain.BaseEntity;
import uz.pdp.domain.entity.history.History;
import uz.pdp.domain.entity.order.Order;
import uz.pdp.domain.entity.user.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "products")
@Builder
public class Product extends BaseEntity {
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Positive
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @OneToOne(mappedBy = "product")
    private History history;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
