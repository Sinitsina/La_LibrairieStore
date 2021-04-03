package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.mapper.CatalogMapper;
import ru.lalibrairiestore.dto.GeneralCatalogDTO;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.model.catalog.*;
import ru.lalibrairiestore.repository.catalog.*;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

    private CoverRepository coverRepository;

    private FormatRepository formatRepository;

    private ManufacturerRepository manufacturerRepository;

    private PaperTypeRepository paperTypeRepository;

    private SheetsTypeRepository sheetsTypeRepository;

    private CatalogMapper catalogMapper;

    @Autowired
    public void setCoverRepository(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }

    @Autowired
    public void setFormatRepository(FormatRepository formatRepository) {
        this.formatRepository = formatRepository;
    }

    @Autowired
    public void setManufacturerRepository(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Autowired
    public void setPaperTypeRepository(PaperTypeRepository paperTypeRepository) {
        this.paperTypeRepository = paperTypeRepository;
    }

    @Autowired
    public void setSheetsTypeRepository(SheetsTypeRepository sheetsTypeRepository) {
        this.sheetsTypeRepository = sheetsTypeRepository;
    }

    @Autowired
    public void setCatalogMapper(CatalogMapper catalogMapper) {
        this.catalogMapper = catalogMapper;
    }

    /**
     * Add new cover type
     */
    public GeneralCatalogDTO addCoverType(String name) {

        if (!coverRepository.existsByName(name)) {

            Cover cover = new Cover();
            cover.setName(name);
            cover.setDeleted(false);
            coverRepository.save(cover);

            log.info("IN addCoverType - cover: {} successfully added", cover);
            return catalogMapper.catalogToCatalogsDTO(cover);

        } else {
            throw new BadRequestException("This cover already exists, check cover name.");
        }
    }

    /**
     * Add new format type
     */
    public GeneralCatalogDTO addFormatType(String name) {

        if (!formatRepository.existsByName(name)) {

            Format format = new Format();
            format.setName(name);
            format.setDeleted(false);
            formatRepository.save(format);

            log.info("IN addFormatType - format: {} successfully added", format);
            return catalogMapper.catalogToCatalogsDTO(format);

        } else {
            throw new BadRequestException("This format already exists, check format name.");
        }
    }


    /**
     * Add new manufacturer
     */
    public GeneralCatalogDTO addManufacturer(String name) {

        if (!manufacturerRepository.existsByName(name)) {

            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(name);
            manufacturer.setDeleted(false);
            manufacturerRepository.save(manufacturer);

            log.info("IN addManufacturer - manufacturer: {} successfully added", manufacturer);
            return catalogMapper.catalogToCatalogsDTO(manufacturer);

        } else {
            throw new BadRequestException("This manufacturer already exists, check manufacturer name.");
        }
    }


    /**
     * Add new paper types
     */
    public GeneralCatalogDTO addPaperType(String name) {

        if (!paperTypeRepository.existsByName(name)) {

            PaperType paperType = new PaperType();
            paperType.setName(name);
            paperType.setDeleted(false);
            paperTypeRepository.save(paperType);

            log.info("IN addPaperType - paper type: {} successfully added", paperType);
            return catalogMapper.catalogToCatalogsDTO(paperType);

        } else {
            throw new BadRequestException("This paper type already exists, check paper type name.");
        }
    }

    /**
     * Add new sheets types
     */
    public GeneralCatalogDTO addSheetsType(String name) {

        if (!sheetsTypeRepository.existsByName(name)) {

            SheetsType sheetsType = new SheetsType();
            sheetsType.setName(name);
            sheetsType.setDeleted(false);
            sheetsTypeRepository.save(sheetsType);

            log.info("IN addSheetsType - sheets type: {} successfully added", sheetsType);
            return catalogMapper.catalogToCatalogsDTO(sheetsType);

        } else {
            throw new BadRequestException("This sheets type already exists, check sheets type name.");
        }
    }

    /**
     * Editing of cover types
     */
    public GeneralCatalogDTO editCoverType(Long coverTypeId, String name) {

        Cover cover = coverRepository
                .findById(coverTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Cover id " + coverTypeId + " not found."));

        if (!coverRepository.existsByName(name)) {

            cover.setName(name);
            coverRepository.save(cover);

            log.info("IN editCoverType - cover: {} successfully edited", cover);
            return catalogMapper.catalogToCatalogsDTO(cover);

        } else {
            throw new BadRequestException("Cover with that name already exists.");
        }
    }

    /**
     * Editing of format types
     */
    public GeneralCatalogDTO editFormatType(Long formatTypeId, String name) {

        Format format = formatRepository
                .findById(formatTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Format id " + formatTypeId + " not found."));

        if (!formatRepository.existsByName(name)) {

            format.setName(name);
            formatRepository.save(format);

            log.info("IN editFormatType - format: {} successfully edited", format);
            return catalogMapper.catalogToCatalogsDTO(format);

        } else {
            throw new BadRequestException("Format with that name already exists.");
        }
    }

    /**
     * Editing of manufacturers
     */
    public GeneralCatalogDTO editManufacturer(Long manufacturerId, String name) {

        Manufacturer manufacturer = manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new EntityNotFoundException("Format id " + manufacturerId + " not found."));

        if (!manufacturerRepository.existsByName(name)) {

            manufacturer.setName(name);
            manufacturerRepository.save(manufacturer);

            log.info("IN editManufacturer - manufacturer: {} successfully edited", manufacturer);
            return catalogMapper.catalogToCatalogsDTO(manufacturer);

        } else {
            throw new BadRequestException("Manufacturer with that name already exists.");
        }

    }

    /**
     * Editing of paper types
     */
    public GeneralCatalogDTO editPaperType(Long paperTypeId, String name) {

        PaperType paperType = paperTypeRepository
                .findById(paperTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Paper type id " + paperTypeId + " not found."));

        if (!paperTypeRepository.existsByName(name)) {

            paperType.setName(name);
            paperTypeRepository.save(paperType);

            log.info("IN editPaperType - paper type: {} successfully edited", paperType);
            return catalogMapper.catalogToCatalogsDTO(paperType);

        } else {
            throw new BadRequestException("Paper type with that name already exists.");
        }
    }

    /**
     * Editing of sheets types
     */
    public GeneralCatalogDTO editSheetsType(Long sheetsTypeId, String name) {

        SheetsType sheetsType = sheetsTypeRepository
                .findById(sheetsTypeId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Sheets type id " + sheetsTypeId + " not found."));

        if (!sheetsTypeRepository.existsByName(name)) {

            sheetsType.setName(name);
            sheetsTypeRepository.save(sheetsType);

            log.info("IN editSheetsType - sheets type: {} successfully edited", sheetsType);
            return catalogMapper.catalogToCatalogsDTO(sheetsType);

        } else {
            throw new BadRequestException("Sheets type with that name already exists.");
        }
    }

    /**
     * Delete cover types
     */
    public void deleteCoverType(Long coverTypeId) {

        Cover cover = coverRepository
                .findById(coverTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Cover id " + coverTypeId + " not found."));

        cover.setDeleted(true);
        coverRepository.save(cover);
        log.info("IN deleteCoverType - cover: {} successfully deleted", cover);
    }

    /**
     * Delete format types
     */
    public void deleteFormatType(Long formatTypeId) {

        Format format = formatRepository
                .findById(formatTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Format id " + formatTypeId + " not found."));

        format.setDeleted(true);
        formatRepository.save(format);
        log.info("IN deleteFormatType - format: {} successfully deleted", format);
    }

    /**
     * Delete manufacturer
     */
    public void deleteManufacturer(Long manufacturerId) {

        Manufacturer manufacturer = manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer id " + manufacturerId + " not found."));

        manufacturer.setDeleted(true);
        manufacturerRepository.save(manufacturer);
        log.info("IN deleteManufacturer - manufacturer: {} successfully deleted", manufacturer);
    }

    /**
     * Delete paper type
     */
    public void deletePaperType(Long paperTypeId) {

        PaperType paperType = paperTypeRepository
                .findById(paperTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Paper type id " + paperTypeId + " not found."));

        paperType.setDeleted(true);
        paperTypeRepository.save(paperType);
        log.info("IN deletePaperType - paper type: {} successfully deleted", paperType);
    }

    /**
     * Delete sheets type
     */
    public void deleteSheetsType(Long sheetsTypeId) {

        SheetsType sheetsType = sheetsTypeRepository
                .findById(sheetsTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Sheets type id " + sheetsTypeId + " not found."));

        sheetsType.setDeleted(true);
        sheetsTypeRepository.save(sheetsType);
        log.info("IN deleteSheetsType - sheets type: {} successfully deleted", sheetsType);
    }

    /**
     * Get list of covers
     *
     * @return List<GeneralCatalogDTO>
     */
    public List<GeneralCatalogDTO> findCovers() {

        return catalogMapper.catalogsToCatalogsDTO(coverRepository.findAll(Sort.by("name").ascending()));
    }

    /**
     * Get list of formats
     *
     * @return List<GeneralCatalogDTO>
     */
    public List<GeneralCatalogDTO> findFormats() {

        return catalogMapper.catalogsToCatalogsDTO(formatRepository.findAll(
                Sort.by("name").ascending()));
    }

    /**
     * Get list of manufacturers
     *
     * @return List<GeneralCatalogDTO>
     */
    public List<GeneralCatalogDTO> findManufacturers() {

        return catalogMapper.catalogsToCatalogsDTO(manufacturerRepository.findAll(
                Sort.by("name").ascending()));
    }

    /**
     * Get list of paper types
     *
     * @return List<GeneralCatalogDTO>
     */
    public List<GeneralCatalogDTO> findPaperTypes() {

        return catalogMapper.catalogsToCatalogsDTO(paperTypeRepository.findAll(
                Sort.by("name").ascending()));
    }

    /**
     * Get list of sheets types
     *
     * @return List<GeneralCatalogDTO>
     */
    public List<GeneralCatalogDTO> findSheetsTypes() {

        return catalogMapper.catalogsToCatalogsDTO(sheetsTypeRepository.findAll(
                Sort.by("name").ascending()));
    }
}
