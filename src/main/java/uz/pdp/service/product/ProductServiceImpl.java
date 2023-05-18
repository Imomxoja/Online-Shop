package uz.pdp.service.product;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.dao.product.ProductDao;
import uz.pdp.dao.user.UserDao;
import uz.pdp.domain.dto.product.ProductCreateDto;
import uz.pdp.domain.dto.product.ProductReadDto;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.product.ProductCategory;
import uz.pdp.domain.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao dao;
    private final UserDao userDao;
    private final ModelMapper mapper;

    @Override
    public ProductReadDto create(ProductCreateDto dto) {
        User user = userDao.findById(dto.getUserId()).get();
        Product map = mapper.map(dto, Product.class);
        map.setUser(user);
        return mapper.map(dao.create(map), ProductReadDto.class);
    }

    @Override
    public ProductReadDto getById(UUID id) {
        try {
            Optional<Product> product = dao.findById(id);
            Product product1 = product.get();
            return mapper.map(product1, ProductReadDto.class);
        } catch (NoResultException | NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public int delete(UUID id) {
        return dao.deleteById(id);
    }

    @Override
    public List<ProductReadDto> getAll() {
        List<ProductReadDto> products = new ArrayList<>();
        List<Product> all = dao.getAll();
        for (Product product : all) {
            ProductReadDto map = mapper.map(product, ProductReadDto.class);
            products.add(map);
        }
        return products;
    }

    @Override
    public ProductReadDto findByName(String name) {
        try {
            Optional<Product> product = dao.findByName(name);
            if (product.isPresent()) {
                Product product1 = product.get();
                return mapper.map(product1, ProductReadDto.class);
            }
        } catch (NoResultException | NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void update(ProductCreateDto dto, UUID productId) {
        Product map = mapper.map(dto, Product.class);
        map.setId(productId);
        dao.update(map);
    }

    @Override
    public List<ProductReadDto> getByCategoryName(String category) {
        List<Product> products = null;
        for (ProductCategory value : ProductCategory.values()) {
            if (value.name().equals(category)) {
                products = dao.getByCategoryName(value);
            }
        }

        List<ProductReadDto> productReadDtos = new ArrayList<>();

        for (Product product : products) {
            ProductReadDto map = mapper.map(product, ProductReadDto.class);
            productReadDtos.add(map);
        }
        return productReadDtos;
    }

    @Override
    public List<ProductReadDto> searchByPartOfNameProduct(String part) {
        List<Product> products = dao.searchByPartOfNameProduct(part);
        List<ProductReadDto> productReadDtos = new ArrayList<>();

        for (Product product : products) {
            ProductReadDto map = mapper.map(product, ProductReadDto.class);
            productReadDtos.add(map);
        }
        return productReadDtos;
    }
}
