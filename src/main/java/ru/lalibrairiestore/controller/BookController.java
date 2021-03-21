package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.BookDTO;
import ru.lalibrairiestore.dto.PageDTO;
import ru.lalibrairiestore.dto.SortingParams;
import ru.lalibrairiestore.model.Genre;
import ru.lalibrairiestore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api("Book controller")
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

    @GetMapping("/all-author")
    @ApiOperation("find all books by author")
    public ResponseEntity<List<BookDTO>> findBookByAuthor(@RequestBody String author) {

        return new ResponseEntity<>(bookService.findBookByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/title")
    @ApiOperation("find the book by title")
    public ResponseEntity<BookDTO> findBookByTitle(@RequestBody String title) {

        return new ResponseEntity<>(bookService.findBookByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/all-genre")
    @ApiOperation("find all books by genre")
    public ResponseEntity<PageDTO<BookDTO>> findBookByGenre(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "PRICE_INCREASE") SortingParams sortingParams,
            @RequestBody Genre genre) {

        return new ResponseEntity<>(bookService.findBookByGenre(genre, pageNo, pageSize, sortingParams), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    @ApiOperation("find book by id")
    public BookDTO findBook(@PathVariable Long bookId) {

        return bookService.findBook(bookId);
    }

    @PostMapping("/add-book")
    @ApiOperation("add new book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {

        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    @ApiOperation("change book information")
    public ResponseEntity<BookDTO> editBook(@PathVariable Long bookId,
                                            @RequestBody BookDTO bookDTO) {

        return new ResponseEntity<>(bookService.editBook(bookId, bookDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation("delete book")
    public void deleteBook(@PathVariable Long bookId) {

        bookService.deleteBook(bookId);
    }

//    @GetMapping("/all-price")
//    @ApiOperation("find all books by price")
//    public ResponseEntity<PageDTO<BookDTO>> findBookByPrice(
//            @RequestParam(defaultValue = "0") int pageNo,
//            @RequestParam(defaultValue = "10") int pageSize,
//            @RequestParam(required = false, defaultValue = "PRICE_INCREASE") SortingParams sortingParams,
//            @RequestBody BigDecimal min, BigDecimal max) {
//
//        return new ResponseEntity<>(bookService.findBookByPrice(min, max, pageNo, pageSize, sortingParams), HttpStatus.OK);
//    }

}
