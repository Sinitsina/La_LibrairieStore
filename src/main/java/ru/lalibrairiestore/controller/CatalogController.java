package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.GeneralCatalogDTO;
import ru.lalibrairiestore.service.CatalogService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static ru.lalibrairiestore.model.Role.ROLE_ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogs")
@Api(value = "Catalog", tags = {"Catalog"})
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/covers")
    public ResponseEntity<GeneralCatalogDTO> addCoverType(@Validated(GeneralCatalogDTO.New.class)
                                                          @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .addCoverType(generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/formats")
    public ResponseEntity<GeneralCatalogDTO> addFormatType(@Validated(GeneralCatalogDTO.New.class)
                                                           @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .addFormatType(generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/manufacturers")
    public ResponseEntity<GeneralCatalogDTO> addManufacturer(@Validated(GeneralCatalogDTO.New.class)
                                                             @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService.addManufacturer(generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/paper-types")
    public ResponseEntity<GeneralCatalogDTO> addPaperType(@Validated(GeneralCatalogDTO.New.class)
                                                          @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService.addPaperType(generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/sheets-types")
    public ResponseEntity<GeneralCatalogDTO> addSheetsType(@Validated(GeneralCatalogDTO.New.class)
                                                           @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService.addSheetsType(generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/covers/{coverTypeId}")
    public ResponseEntity<GeneralCatalogDTO> editCoverType(@PathVariable Long coverTypeId,
                                                           @Validated(GeneralCatalogDTO.Exist.class)
                                                           @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .editCoverType(coverTypeId, generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/formats/{formatId}")
    public ResponseEntity<GeneralCatalogDTO> editFormatType(@PathVariable Long formatId,
                                                            @Validated(GeneralCatalogDTO.Exist.class)
                                                            @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .editFormatType(formatId, generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/manufacturers/{manufacturerId}")
    public ResponseEntity<GeneralCatalogDTO> editManufacturer(@PathVariable Long manufacturerId,
                                                              @Validated(GeneralCatalogDTO.Exist.class)
                                                              @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .editManufacturer(manufacturerId, generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/paper-types/{paperTypeId}")
    public ResponseEntity<GeneralCatalogDTO> editPaperType(@PathVariable Long paperTypeId,
                                                           @Validated(GeneralCatalogDTO.Exist.class)
                                                           @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .editPaperType(paperTypeId, generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/sheets-types/{sheetsTypeId}")
    public ResponseEntity<GeneralCatalogDTO> editSheetsType(@PathVariable Long sheetsTypeId,
                                                            @Validated(GeneralCatalogDTO.Exist.class)
                                                            @RequestBody GeneralCatalogDTO generalCatalogDTO) {

        return new ResponseEntity<>(catalogService
                .editSheetsType(sheetsTypeId, generalCatalogDTO.getName()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/covers/{coverId}")
    public void deleteCoverType(@PathVariable Long coverId) {

        catalogService.deleteCoverType(coverId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/formats/{formatId}")
    public void deleteFormatType(@PathVariable Long formatId) {

        catalogService.deleteFormatType(formatId);
    }


    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/manufacturers/{manufacturerId}")
    public void deleteManufacturer(@PathVariable Long manufacturerId) {

        catalogService.deleteManufacturer(manufacturerId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/paper-types/{paperTypeId}")
    public void deletePaperType(@PathVariable Long paperTypeId) {

        catalogService.deletePaperType(paperTypeId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/sheets-types/{sheetsTypeId}")
    public void deleteSheetsType(@PathVariable Long sheetsTypeId) {

        catalogService.deleteSheetsType(sheetsTypeId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/covers")
    public List<GeneralCatalogDTO> findCovers() {

        return catalogService.findCovers();
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/formats")
    public List<GeneralCatalogDTO> findFormats() {

        return catalogService.findFormats();
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/manufacturers")
    public List<GeneralCatalogDTO> findManufacturers() {

        return catalogService.findManufacturers();
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/paper-types")
    public List<GeneralCatalogDTO> findPaperTypes() {

        return catalogService.findPaperTypes();
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/sheets-types")
    public List<GeneralCatalogDTO> findSheetsTypes() {

        return catalogService.findSheetsTypes();
    }
}