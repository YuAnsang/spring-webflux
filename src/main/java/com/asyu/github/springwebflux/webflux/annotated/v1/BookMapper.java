package com.asyu.github.springwebflux.webflux.annotated.v1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookPostToBook(BookDto.Post requestBody);

    Book bookPatchToBook(BookDto.Patch requestBody);

    BookDto.Response bookToResponse(Book book);

    default Mono<BookDto.Response> bookToBookResponse(Mono<Book> mono) {
        return mono.flatMap(book -> Mono.just(bookToResponse(book)));
    }
}
