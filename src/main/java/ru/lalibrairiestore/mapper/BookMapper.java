package ru.lalibrairiestore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lalibrairiestore.dto.BookDTO;
import ru.lalibrairiestore.model.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    List<BookDTO> map(List<Book> books);

    BookDTO bookToBookDto(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

}
