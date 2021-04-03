package ru.lalibrairiestore.repository.catalog;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.catalog.GeneralCatalog;
import ru.lalibrairiestore.model.catalog.Manufacturer;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

//    List<GeneralCatalog> findAllManufacturers(Sort sort);

//    boolean exists(String ManufacturerName);
}
