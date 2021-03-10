package ru.lalibrairiestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lalibrairiestore.model.Book;


public interface BookRepository extends JpaRepository<Book, Long>,
        PagingAndSortingRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);
//    Iterable<Book> findAll(Sort sort);

}
