package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.CartItemDTO;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.CartItemMapper;
import ru.lalibrairiestore.model.CartItem;
import ru.lalibrairiestore.model.Order;
import ru.lalibrairiestore.model.OrderStatus;
import ru.lalibrairiestore.model.Product;
import ru.lalibrairiestore.repository.CartItemRepository;
import ru.lalibrairiestore.repository.OrderRepository;
import ru.lalibrairiestore.repository.ProductRepository;
import ru.lalibrairiestore.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private CartItemMapper cartItemMapper;

    private OrderService orderService;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCartItemMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Add product to cart
     */
    public CartItemDTO addToCart(Long productId, Long userId) {

        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product id " + productId + " doesn't exist.");
        }

        Order userOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        if (userOrder == null) {
            userOrder = new Order();
            userOrder.setOrderStatus(OrderStatus.CREATED);
            userOrder.setUser(userRepository.findUserById(userId));

            orderRepository.save(userOrder);
        }

        List<Product> productList = new ArrayList<>();

//        Get all carts from order and add products from car to productList
        userOrder.getCartItems().forEach(e -> productList.add(e.getProduct()));

//        If product is on cart, increase quantity by 1, else add product to cart with quantity 1
        if (productList.stream().anyMatch(productRepository.findProductById(productId)::equals)) {

            CartItem cartItem = cartItemRepository.getByOrderAndProductId(userOrder, productId);
            cartItem.setCount(cartItem.getCount() + 1);
            cartItemRepository.save(cartItem);

            return cartItemMapper.cartItemToCartItemDTO(cartItem);

        } else {
            CartItem cart = new CartItem();
            cart.setProduct(productRepository.findProductById(productId));
            cart.setCount(1);
            cart.setOrder(userOrder);
            cart.setCartItemId(new CartItem.CartItemId(userOrder.getId(), productId));
            cartItemRepository.save(cart);

            return cartItemMapper.cartItemToCartItemDTO(cart);
        }
    }

    /**
     * Changing quantity of products in cart.
     */
    public int changeProductCount(Long productId, int count, Long userId) {

        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);
        List<CartItem> cartItem = order.getCartItems();

        for (CartItem item : cartItem) {
            if (item.getProduct().equals(productRepository.findById(productId).get())) {
                item.setCount(count);
            }
        }
        order.setCartItems(cartItem);
        orderRepository.save(order);

        return count;
    }

    /**
     * Delete product from cart.
     */
    public void deleteProductFromCart(Long productId, Long userId) {

        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        if (order == null) {
            throw new EntityNotFoundException("Cart is empty!");
        }

        List<CartItem> cartItemList = order.getCartItems();
        cartItemList.remove(cartItemRepository.getByOrderAndProductId(order, productId));

        order.setCartItems(cartItemList);
        orderRepository.save(order);
    }

    /**
     * Delete all products from cart.
     */
    public void deleteAllProductsFromCart(Long userId) {

        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        if (order == null) {
            throw new EntityNotFoundException("Cart is empty!");
        }

        List<CartItem> cartItemList = order.getCartItems();
        cartItemList.clear();

        order.setCartItems(cartItemList);
        orderRepository.save(order);
    }

    /**
     * Show all products from cart.
     */
    public CartItemDTO showAllProductsInCartItem(Long userId) {

        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        if (order == null) {
            throw new BadRequestException("Cart item is empty!");
        }

        List<CartItem> cartItem = order.getCartItems();

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartItemDTO(cartItemMapper.itemsListToCartItemDTO(cartItem));
        cartItemDTO.setOrderPrice(orderService.calculateOrderPrice(order));

        return cartItemDTO;
    }
}
