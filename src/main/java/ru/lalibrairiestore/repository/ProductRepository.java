package ru.lalibrairiestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lalibrairiestore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Product findProductById(Long id);

    boolean existsByTitle(String title);
}
