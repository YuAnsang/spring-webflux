package com.asyu.github.springwebflux.webflux.router.v1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookPostToBook(BookDto.Post requestBody);

    Book bookPatchToBook(BookDto.Patch requestBody);

    BookDto.Response bookToResponse(Book book);

    List<BookDto.Response> booksToResponse(List<Book> books);

}
