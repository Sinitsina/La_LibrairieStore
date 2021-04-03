package ru.lalibrairiestore.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.lalibrairiestore.dto.BookDTO;
import ru.lalibrairiestore.model.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @IterableMapping(qualifiedByName = "bookListToDTOBookList")
    List<BookDTO> bookListToDTOBookList(List<Book> books);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

    default Page<BookDTO> bookToBooksDTO(Page<Book> books) {
        return books.map(this::bookToBookDTO);
    }

}
