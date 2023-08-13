package com.asyu.github.springwebflux.webflux.router.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service("bookServiceV3")
public class BookService {

    private final BookRepository bookRepository;

    public Mono<Book> saveBook(Book book) {
        return verifyExistIsbn(book.getIsbn())
                .then(bookRepository.save(book));
    }

    public Mono<Book> updateBook(Book book) {
        return findVerifiedBook(book.getBookId())
                .flatMap(bookRepository::save);
    }

    public Mono<Book> findBook(long bookId) {
        return findVerifiedBook(bookId);
    }

    public Mono<List<Book>> findBooks() {
        return bookRepository.findAll().collectList();
    }

    private Mono<Void> verifyExistIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .flatMap(findBook -> {
                    if (findBook != null) {
                        return Mono.error(new RuntimeException());
                    }
                    return Mono.empty();
                });
    }

    private Mono<Book> findVerifiedBook(long bookId) {
        return bookRepository
                .findById(bookId)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }
}
