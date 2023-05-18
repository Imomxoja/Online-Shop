package uz.pdp.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.domain.entity.product.ProductCategory;
import uz.pdp.domain.entity.user.User;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {
    String name;
    String description;
    Double price;
    ProductCategory category;
    UUID userId;
}
