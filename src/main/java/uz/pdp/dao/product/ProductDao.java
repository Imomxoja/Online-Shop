package uz.pdp.dao.product;

import org.springframework.stereotype.Repository;
import uz.pdp.dao.BaseDao;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.product.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDao extends BaseDao<Product, UUID> {
    Optional<Product> findByName(String name);

    List<Product> getByCategoryName(ProductCategory category);


    List<Product> searchByPartOfNameProduct(String part);
}
