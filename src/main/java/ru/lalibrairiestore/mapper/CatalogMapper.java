package ru.lalibrairiestore.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lalibrairiestore.dto.GeneralCatalogDTO;
import ru.lalibrairiestore.model.catalog.GeneralCatalog;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    GeneralCatalogDTO catalogToCatalogsDTO(GeneralCatalog generalCatalog);

    @IterableMapping(qualifiedByName = "catalogToCatalogDTO")
    List<GeneralCatalogDTO> catalogsToCatalogsDTO(List<? extends GeneralCatalog> catalogs);
}