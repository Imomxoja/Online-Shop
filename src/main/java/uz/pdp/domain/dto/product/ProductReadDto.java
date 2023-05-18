package uz.pdp.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.domain.entity.product.ProductCategory;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReadDto {
    UUID id;
    UUID userId;
    String name;
    String description;
    ProductCategory category;
    Double price;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
