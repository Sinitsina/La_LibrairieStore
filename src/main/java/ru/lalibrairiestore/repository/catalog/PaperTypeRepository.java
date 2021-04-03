package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.GeneralCatalog;
import ru.lalibrairiestore.model.catalog.PaperType;

import java.util.List;

public interface PaperTypeRepository extends JpaRepository<PaperType, Long> {

//    List<GeneralCatalog> findAllPaperTypes(Sort sort);

//    boolean exists(String PaperTypeName);
}
