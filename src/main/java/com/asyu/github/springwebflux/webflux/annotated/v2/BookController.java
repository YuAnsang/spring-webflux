package com.asyu.github.springwebflux.webflux.annotated.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/v2/books")
@RestController("bookControllerV2")
public class BookController {

    private final BookService bookService;

    private final BookMapper mapper = BookMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto.Response> postBook(@RequestBody Mono<BookDto.Post> requestBody) {
        Mono<Book> result = bookService.createBook(requestBody);
        return result.flatMap(book -> Mono.just(mapper.bookToResponse(book)));
    }

    @PatchMapping("/{book-id}")
    public Mono<BookDto.Response> patchBook(@PathVariable("book-id") long bookId,
                          @RequestBody Mono<BookDto.Patch> requestBody) {
        Mono<Book> result = bookService.updateBook(bookId, requestBody);
        return result.flatMap(book -> Mono.just(mapper.bookToResponse(book)));
    }

    @GetMapping("/{book-id}")
    public Mono<BookDto.Response> getBook(@PathVariable("book-id") long bookId) {
        return bookService.findBook(bookId)
                .flatMap(book -> Mono.just(mapper.bookToResponse(book)));
    }

}
