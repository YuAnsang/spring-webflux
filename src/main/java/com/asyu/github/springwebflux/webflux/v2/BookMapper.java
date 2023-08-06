package com.asyu.github.springwebflux.webflux.v2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookPostToBook(BookDto.Post requestBody);

    Book bookPatchToBook(BookDto.Patch requestBody);

    BookDto.Response bookToResponse(Book book);

}
