package uz.pdp.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @param <CD> This is Creation DTO
 * @param <RD> This is Read DTO
 * @author Imomxo'ja
 */
@Service
public interface BaseService<CD, RD> {
    RD create(CD cd);

    RD getById(UUID id);

    int delete(UUID id);

    List<RD> getAll();
}
