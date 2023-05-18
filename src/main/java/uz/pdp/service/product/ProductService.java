package uz.pdp.service.product;

import org.springframework.stereotype.Service;
import uz.pdp.domain.dto.product.ProductCreateDto;
import uz.pdp.domain.dto.product.ProductReadDto;
import uz.pdp.service.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService extends BaseService<ProductCreateDto, ProductReadDto> {
    ProductReadDto findByName(String name);

    void update(ProductCreateDto dto, UUID productId);

    List<ProductReadDto> getByCategoryName(String category);


    List<ProductReadDto> searchByPartOfNameProduct(String part);
}
