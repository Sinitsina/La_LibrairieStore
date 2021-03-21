package ru.lalibrairiestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lalibrairiestore.model.Book;
import ru.lalibrairiestore.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>,
        PagingAndSortingRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByGenres(Genre genres, Pageable pageable);

    Optional<List<Book>> findAllByAuthor(String author);

    Optional<Book> findAllByTitle(String title);

    boolean existsByTitle(String title);

}
