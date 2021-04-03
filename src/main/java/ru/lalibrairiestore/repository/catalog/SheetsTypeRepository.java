package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.SheetsType;

import java.util.List;

public interface SheetsTypeRepository extends JpaRepository<SheetsType, Long> {

    List<SheetsType> findAll(Sort sort);

    boolean existsByName(String name);
}
