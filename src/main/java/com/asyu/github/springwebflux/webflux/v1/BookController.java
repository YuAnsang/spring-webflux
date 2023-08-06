package com.asyu.github.springwebflux.webflux.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/v1/books")
@RestController
public class BookController {

    private final BookService bookService;

    private final BookMapper mapper = BookMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto.Response> postBook(@RequestBody BookDto.Post requestBody) {
        Mono<Book> book = bookService.createBook(mapper.bookPostToBook(requestBody));
        return mapper.bookToBookResponse(book);
    }

    @PatchMapping("/{book-id}")
    public Mono<BookDto.Response> patchBook(@PathVariable("book-id") long bookId,
                                            @RequestBody BookDto.Patch requestBody) {
        requestBody.setBookId(bookId);
        Mono<Book> book = bookService.updateBook(mapper.bookPatchToBook(requestBody));

        return mapper.bookToBookResponse(book);
    }

    @GetMapping("/{book-id}")
    public Mono<BookDto.Response> getBook(@PathVariable("book-id") long bookId) {
        Mono<Book> book = bookService.findBook(bookId);
        return mapper.bookToBookResponse(book);
    }

}
