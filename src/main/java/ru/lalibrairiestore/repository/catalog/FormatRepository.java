package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.Format;

import java.util.List;

public interface FormatRepository extends JpaRepository<Format, Long> {

    List<Format> findAll(Sort sort);

    boolean existsByName(String name);
}
