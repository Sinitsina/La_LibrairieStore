package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
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

import java.util.List;

@Service
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
    public ProductDTO addProductToFavourites(Long productId, Long userId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        User user = userRepository.findUserById(userId);

        List<Product> favouriteProducts = user.getFavouriteProducts();

        if (favouriteProducts.contains(productRepository.findProductById(productId))) {
            throw new BadRequestException("This product is on favourite list already!");
        }

        favouriteProducts.add(product);
        user.setFavouriteProducts(favouriteProducts);

        userRepository.save(user);

        return productMapper.productToProductDTO(product);
    }

    /**
     * Delete product from favourites
     */
    public void deleteProductFromFavourites(Long productId, Long userId) {

        User user = userRepository.findUserById(userId);
        List<Product> favouriteProducts = user.getFavouriteProducts();

        if (favouriteProducts.contains(productRepository.findProductById(productId))) {
            favouriteProducts.remove(productRepository.findProductById(productId));
            user.setFavouriteProducts(favouriteProducts);

            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("This product isn't on your favourite list!");
        }

    }

    /**
     * Show all favourite products by user id
     */
    public List<ProductDTO> showFavouriteProducts(Long userId) {

        User user = userRepository.findUserById(userId);
        List<Product> products = user.getFavouriteProducts();

        if (products.isEmpty()) {
            throw new EntityNotFoundException("You don't have any favourite products.");
        }

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

            return productMapper.productToProductDTO(product);
        } else {
            throw new BadRequestException("Product already exist.");
        }
    }

    /**
     * Edit product
     */
    public ProductDTO editProduct(Long productId, ProductDTO productDTO) {

        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        productRepository.save(productMapper.productDTOToProduct(productDTO));

        return productMapper.productToProductDTO(productRepository.getOne(productId));
    }

    /**
     * Delete product (change isDeleted parameter to true)
     */
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("The product id " + productId + " was not found"));

        product.setDeleted(true);
        productRepository.save(product);
    }

}
