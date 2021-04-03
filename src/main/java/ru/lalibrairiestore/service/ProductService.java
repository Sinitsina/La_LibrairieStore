package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.ProductDTO;
import ru.lalibrairiestore.dto.SortingParams;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.ProductMapper;
import ru.lalibrairiestore.model.Product;
import ru.lalibrairiestore.model.User;
import ru.lalibrairiestore.repository.ProductRepository;
import ru.lalibrairiestore.repository.UserRepository;
import ru.lalibrairiestore.security.JwtUser;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private ProductMapper productMapper;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * Sorting method for different products(books, notebooks etc)
     */
    public PageRequest sortingWithParams(SortingParams sortingParams, int page, int pageSize) {

        return switch (sortingParams) {
            case PRICE_INCREASE -> PageRequest.of(page, pageSize, Sort.by("price").ascending());
            case PRICE_DECREASE -> PageRequest.of(page, pageSize, Sort.by("price").descending());
            case TITLE_INCREASE -> PageRequest.of(page, pageSize, Sort.by("title").ascending());
            case TITLE_DECREASE -> PageRequest.of(page, pageSize, Sort.by("title").descending());
            default -> PageRequest.of(page, pageSize, Sort.unsorted());
        };
    }

    /**
     * Add product to favourites
     */
    public ProductDTO addProductToFavourites(Long productId) {

        User user = userRepository.findUserById(JwtUser.getCurrentUserID());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        List<Product> favouriteProducts = user.getFavouriteProducts();

        if (favouriteProducts.contains(productRepository.findProductById(productId))) {
            throw new BadRequestException("This product is on favourite list already!");
        }

        favouriteProducts.add(product);
        user.setFavouriteProducts(favouriteProducts);

        userRepository.save(user);
        log.info("IN addProductToFavourites - product {} successfully added to favourites", product);
        return productMapper.productToProductDTO(product);
    }

    /**
     * Delete product from favourites
     */
    public void deleteProductFromFavourites(Long productId) {

        User user = userRepository.findUserById(JwtUser.getCurrentUserID());
        List<Product> favouriteProducts = user.getFavouriteProducts();
        Product product = productRepository.findProductById(productId);

        if (favouriteProducts.contains(product)) {
            favouriteProducts.remove(productRepository.findProductById(productId));
            user.setFavouriteProducts(favouriteProducts);

            userRepository.save(user);
            log.info("IN deleteProductFromFavourites - product {} successfully deleted from favourites", product);

        } else {
            throw new EntityNotFoundException("This product isn't on your favourite list!");
        }

    }

    /**
     * Show all favourite products by user id
     */
    public List<ProductDTO> showFavouriteProducts() {

        User user = userRepository.findUserById(JwtUser.getCurrentUserID());
        List<Product> products = user.getFavouriteProducts();

        if (products.isEmpty()) {
            throw new EntityNotFoundException("You don't have any favourite products.");
        }

        log.info("IN showFavouriteProducts {} products were found", products.size());

        return productMapper.productListToDTOProductList(products);
    }

    /**
     * Add product
     */
    public ProductDTO addProduct(ProductDTO productDTO) {

        if (!productRepository.existsByTitle(productDTO.getTitle())) {
            Product product = productMapper.productDTOToProduct(productDTO);

            product.setDeleted(false);
            productRepository.save(product);

            log.info("IN addProduct - product {} successfully added to data base.", product);
            return productMapper.productToProductDTO(product);

        } else {
            throw new BadRequestException("Product already exist.");
        }
    }

    /**
     * Edit product
     */
    public ProductDTO editProduct(Long productId, ProductDTO productDTO) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        productDTO.setId(productId);
        productRepository.save(productMapper.productDTOToProduct(productDTO));
        log.info("IN editProduct - product with id {} successfully added to data base.", productId);

        return productMapper.productToProductDTO(product);
    }

    /**
     * Delete product (change isDeleted parameter to true)
     */
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        product.setDeleted(true);
        log.info("IN deleteProduct - product {} successfully deleted.", product);

        productRepository.save(product);
    }
}
