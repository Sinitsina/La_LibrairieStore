package ru.lalibrairiestore.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lalibrairiestore.dto.CartItemDTO;
import ru.lalibrairiestore.dto.CartItemListDTO;
import ru.lalibrairiestore.model.CartItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);

    @IterableMapping(qualifiedByName = "cartItemsToCartItemsDTO")
    List<CartItemDTO> itemsListToCartItemDTO(List<? extends CartItem> items);

    List<CartItemListDTO> itemsListToCartItemListDTO(List<CartItem> items);


}
