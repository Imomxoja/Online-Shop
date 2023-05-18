package uz.pdp.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseDao<E, ID> {
    E create(E e);
    List<E> getAll();

    E update(E e);

    Optional<E> findById(ID id);

    int deleteById(ID id);
}
