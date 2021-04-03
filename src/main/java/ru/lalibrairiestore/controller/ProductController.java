package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.ProductDTO;
import ru.lalibrairiestore.service.ProductService;
import ru.lalibrairiestore.transfer.Exist;
import ru.lalibrairiestore.transfer.New;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static ru.lalibrairiestore.model.Role.ROLE_ADMIN;
import static ru.lalibrairiestore.model.Role.ROLE_CUSTOMER;

@RestController
@RequestMapping("/api/product")
@Api(value = "Product", tags = {"Product"})
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PostMapping("/favourites/{productId}")
    @ApiOperation("add product to favourites")
    public ProductDTO addProductToFavourites(@PathVariable Long productId) {

        return productService.addProductToFavourites(productId);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @DeleteMapping("/favourites/{productId}")
    @ApiOperation("delete product to favourites")
    public void deleteProductFromFavourites(@PathVariable Long productId) {

        productService.deleteProductFromFavourites(productId);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @GetMapping("/favourites")
    @ApiOperation("show all favourite products")
    public List<ProductDTO> showFavouriteProducts() {

        return productService.showFavouriteProducts();
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/add-product")
    @ApiOperation("add new product")
    public ResponseEntity<ProductDTO> addProduct(@Validated(New.class) @RequestBody ProductDTO productDTO) {

        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/{productId}")
    @ApiOperation("change product information")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable Long productId,
                                                  @Validated(Exist.class)
                                                  @RequestBody ProductDTO productDTO) {

        return new ResponseEntity<>(productService.editProduct(productId, productDTO), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/{productId}")
    @ApiOperation("delete product")
    public void deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);
    }
}
