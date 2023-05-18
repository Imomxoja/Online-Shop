package uz.pdp.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.domain.BaseEntity;
import uz.pdp.domain.entity.history.History;
import uz.pdp.domain.entity.order.Order;
import uz.pdp.domain.entity.product.Product;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class User extends BaseEntity {
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private Double balance;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<History> histories;
}