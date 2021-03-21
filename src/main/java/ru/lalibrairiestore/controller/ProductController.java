package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.ProductDTO;
import ru.lalibrairiestore.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Api("Product Controller")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/favourites/{productId}")
    @ApiOperation("add product to favourites")
    public ProductDTO addProductToFavourites(@PathVariable Long productId, Long userId) {

        return productService.addProductToFavourites(productId, userId);
    }

    @DeleteMapping("/favourites/{productId}")
    @ApiOperation("delete product to favourites")
    public void deleteDrinkFromFavourites(@PathVariable Long productId, Long userId) {

        productService.deleteProductFromFavourites(productId, userId);
    }

    @GetMapping("/favourites")
    @ApiOperation("show all favourite products")
    public List<ProductDTO> showFavouriteProducts(Long userId) {

        return productService.showFavouriteProducts(userId);
    }

    @PostMapping("/add-product")
    @ApiOperation("add new product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {

        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    @ApiOperation("change product information")
    public ResponseEntity<ProductDTO> editBook(@PathVariable Long productId,
                                               @RequestBody ProductDTO productDTO) {

        return new ResponseEntity<>(productService.editProduct(productId, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @ApiOperation("delete product")
    public void deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);
    }
}
