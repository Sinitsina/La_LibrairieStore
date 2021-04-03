package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.Format;
import ru.lalibrairiestore.model.catalog.GeneralCatalog;

import java.util.List;

public interface FormatRepository extends JpaRepository<Format, Long> {

//        List<GeneralCatalog> findAllFormats(Sort sort);

//        boolean exists(String FormatName);
}
