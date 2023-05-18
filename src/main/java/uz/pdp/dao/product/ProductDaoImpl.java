package uz.pdp.dao.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.product.ProductCategory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductDaoImpl implements ProductDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Product create(Product product) {
        manager.persist(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return manager.createQuery("select u from products u", Product.class).getResultList();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product product1 = manager.find(Product.class, product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setCategory(product.getCategory());

        return manager.merge(product1);
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return Optional.of(manager.find(Product.class, uuid));
    }

    @Override
    @Transactional
    public int deleteById(UUID uuid) {
       return manager.createQuery("delete from products where id = :id")
                .setParameter("id", uuid)
                .executeUpdate();
    }

    @Override
    public Optional<Product> findByName(String name) {
        Product product = manager.createQuery("select u from products u where u.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.of(product);
    }

    @Override
    public List<Product> getByCategoryName(ProductCategory category) {
        return manager.createQuery("select p from products p" +
                        " where p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Product> searchByPartOfNameProduct(String part) {
        return manager.createQuery("select p from products p where p.name ilike :part", Product.class)
                .setParameter("part", '%' + part + '%')
                .getResultList();
    }
}
