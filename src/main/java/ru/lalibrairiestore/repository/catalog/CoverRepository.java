package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.Cover;

import java.util.List;

public interface CoverRepository extends JpaRepository<Cover, Long> {

    List<Cover> findAll(Sort sort);

    boolean existsByName(String name);
}
