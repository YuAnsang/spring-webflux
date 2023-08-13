package com.asyu.github.springwebflux.webflux.router.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookHandler {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private final BookValidator bookValidator;

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(BookDto.Post.class)
                .doOnNext(post -> bookValidator.validate(post))
                .map(bookMapper::bookPostToBook)
                .flatMap(book -> ServerResponse
                        .created(URI.create("/v3/books/" + book.getBookId()))
                        .build()
                );
    }



}
