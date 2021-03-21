package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.CartItemDTO;
import ru.lalibrairiestore.service.CartItemService;

@RestController
@RequestMapping("/api/cartitem")
@Api("Cart Item controller")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/{productId}")
    @ApiOperation("add product to cart")
    public CartItemDTO addToCart(@PathVariable Long productId, Long userId) {
        return cartItemService.addToCart(productId, userId);
    }

    @PutMapping("/{productId}/quantity")
    @ApiOperation("change product count")
    public ResponseEntity<Integer> changeProductCount(@PathVariable Long productId,
                                                      Long userId,
                                                      @RequestBody CartItemDTO cartItemDTO) {

        return new ResponseEntity<>(cartItemService.changeProductCount(
                productId, cartItemDTO.getCount(), userId), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @ApiOperation("delete product from cart")
    public void deleteProductFromCart(@PathVariable Long productId, Long userId) {

        cartItemService.deleteProductFromCart(productId, userId);
    }

    @DeleteMapping("/all-products")
    @ApiOperation("delete all products from cart")
    public void deleteAllProductsFromCart(Long userId) {

        cartItemService.deleteAllProductsFromCart(userId);
    }


    @GetMapping("/list")
    @ApiOperation("show all products from cart")
    public CartItemDTO showAllProductsInCartItem(Long userId) {

        return cartItemService.showAllProductsInCartItem(userId);
    }

}
