package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.CartItemDTO;
import ru.lalibrairiestore.dto.CartItemListDTO;
import ru.lalibrairiestore.service.CartItemService;

import javax.annotation.security.RolesAllowed;

import static ru.lalibrairiestore.model.Role.ROLE_CUSTOMER;

@RestController
@RequestMapping("/api/cartitem")
@Api(value = "Cart item", tags = {"Cart item"})
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PostMapping("/{productId}")
    @ApiOperation("add product to cart")
    public CartItemDTO addToCart(@PathVariable Long productId) {
        return cartItemService.addToCart(productId);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PutMapping("/{productId}/quantity")
    @ApiOperation("change product count")
    public ResponseEntity<Integer> changeProductCount(@PathVariable Long productId,
                                                      @RequestBody CartItemDTO cartItemDTO) {

        return new ResponseEntity<>(cartItemService.changeProductCount(
                productId, cartItemDTO.getCount()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @DeleteMapping("/{productId}")
    @ApiOperation("delete product from cart")
    public void deleteProductFromCart(@PathVariable Long productId) {

        cartItemService.deleteProductFromCart(productId);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @DeleteMapping("/all-products")
    @ApiOperation("delete all products from cart")
    public void deleteAllProductsFromCart() {

        cartItemService.deleteAllProductsFromCart();
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @GetMapping("/list")
    @ApiOperation("show all products from cart")
    public CartItemListDTO showAllProductsInCartItem() {

        return cartItemService.showAllProductsInCartItem();
    }
}
