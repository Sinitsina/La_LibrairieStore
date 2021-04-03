package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.BookDTO;
import ru.lalibrairiestore.dto.PageDTO;
import ru.lalibrairiestore.dto.ProductDTO;
import ru.lalibrairiestore.dto.SortingParams;
import ru.lalibrairiestore.model.Genre;
import ru.lalibrairiestore.service.BookService;
import ru.lalibrairiestore.transfer.Exist;
import ru.lalibrairiestore.transfer.New;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static ru.lalibrairiestore.model.Role.ROLE_ADMIN;

@RestController
@RequestMapping("/api/books")
@Api(value = "Book", tags = {"Book"})
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    @ApiOperation("find all books method")
    public ResponseEntity<PageDTO<BookDTO>> findAllBook(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "PRICE_INCREASE") SortingParams sortingParams) {

        return new ResponseEntity<>(bookService.findAllBook(pageNo, pageSize, sortingParams), HttpStatus.OK);
    }

    @GetMapping("/all-author/{author}")
    @ApiOperation("find all books by author")
    public ResponseEntity<List<BookDTO>> findBookByAuthor(@PathVariable String author) {

        return new ResponseEntity<>(bookService.findBookByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    @ApiOperation("find the book by title")
    public ResponseEntity<BookDTO> findBookByTitle(@PathVariable String title) {

        return new ResponseEntity<>(bookService.findBookByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/all-genre/{genre}")
    @ApiOperation("find all books by genre")
    public ResponseEntity<PageDTO<BookDTO>> findBookByGenre(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "PRICE_INCREASE") SortingParams sortingParams,
            @PathVariable Genre genre) {

        return new ResponseEntity<>(bookService.findBookByGenre(genre, pageNo, pageSize, sortingParams), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/{bookId}")
    @ApiOperation("find book by id")
    public BookDTO findBook(@PathVariable Long bookId) {

        return bookService.findBook(bookId);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PostMapping("/add-book")
    @ApiOperation("add new book")
    public ResponseEntity<BookDTO> addBook(@Validated(New.class) @RequestBody BookDTO bookDTO) {

        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/{bookId}")
    @ApiOperation("change book information")
    public ResponseEntity<BookDTO> editBook(@PathVariable Long bookId,
                                            @Validated(Exist.class)
                                            @RequestBody BookDTO bookDTO) {

        return new ResponseEntity<>(bookService.editBook(bookId, bookDTO), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @DeleteMapping("/{bookId}")
    @ApiOperation("delete book")
    public void deleteBook(@PathVariable Long bookId) {

        bookService.deleteBook(bookId);
    }
}
