package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.BookDTO;
import ru.lalibrairiestore.dto.PageDTO;
import ru.lalibrairiestore.dto.SortingParams;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.BookMapper;
import ru.lalibrairiestore.model.Book;
import ru.lalibrairiestore.model.Genre;
import ru.lalibrairiestore.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private ProductService productService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Find all books
     */
    public PageDTO<BookDTO> findAllBook(int pageNo, int pageSize, SortingParams sortingParams) {

        Page<Book> books = bookRepository
                .findAll(productService.sortingWithParams(sortingParams, pageNo, pageSize));

        return new PageDTO<>(bookMapper.bookToBooksDTO((books)));
    }

    /**
     * Find all books of an author
     */
    public List<BookDTO> findBookByAuthor(String author) {

        return bookMapper.bookListToDTOBookList(bookRepository.findAllByAuthor(author)
                .orElseThrow(() -> new EntityNotFoundException("There is no books of this author.")));
    }

    /**
     * Find a book by title
     */
    public BookDTO findBookByTitle(String title) {

        return bookMapper.bookToBookDTO(bookRepository.findAllByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("The book was not found.")));
    }

    /**
     * Find all books of certain genre
     */
    public PageDTO<BookDTO> findBookByGenre(Genre genre, int pageNo, int pageSize, SortingParams sortingParams) {

        return new PageDTO<>(bookMapper.bookToBooksDTO(bookRepository.findAllByGenres(genre,
                productService.sortingWithParams(sortingParams, pageNo, pageSize))));
    }

    /**
     * Find a book by id
     */
    public BookDTO findBook(Long bookId) {

        return bookMapper.bookToBookDTO(bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("The book id " + bookId + " was not found")));
    }

    /**
     * Add book
     */
    public BookDTO addBook(BookDTO bookDTO) {

        if (!bookRepository.existsByTitle(bookDTO.getTitle())) {
            Book book = bookMapper.bookDTOToBook(bookDTO);

            book.setDeleted(false);
            bookRepository.save(book);

            return bookMapper.bookToBookDTO(book);
        } else {
            throw new BadRequestException("Book already exist.");
        }
    }

    /**
     * Edit book
     */
    public BookDTO editBook(Long bookId, BookDTO bookDTO) {

        bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("The book id " + bookId + " was not found"));

        bookRepository.save(bookMapper.bookDTOToBook(bookDTO));

        return bookMapper.bookToBookDTO(bookRepository.getOne(bookId));
    }

    /**
     * Delete book (change isDeleted parameter to true)
     */
    public void deleteBook(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("The book id " + bookId + " was not found"));

        book.setDeleted(true);
        bookRepository.save(book);
    }

    /*
    проверить позже, не работает сортировка
     */

//    public PageDTO<BookDTO> findBookByPrice(BigDecimal priceMin, BigDecimal priceMax,
//                                         int pageNo, int pageSize, SortingParams sortingParams) {
//        ProductService productService = new ProductService();
//
//        List<Book> result = bookRepository.findAll().stream()
//                .filter(e -> (e.getPrice().compareTo(priceMin) <= 0  && e.getPrice().compareTo(priceMax) > 0))
//                .collect(Collectors.toList());
//
//        return new PageDTO<>(bookMapper.bookToBooksDTO(new PageImpl<>(result,
//                productService.sortingWithParams(sortingParams, pageNo, pageSize), 10)));
//    }

}
