package com.asyu.github.springwebflux.refactor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class _11_03_Context_Features4 {

  private static final String HEADER_AUTH_TOKEN = "authToken";

  public static void main(String[] args) throws InterruptedException {
    Mono<String> mono = postBook(Mono.just(
        new Book("abcd-1111-3533-2809", "Reactor's Bible", "Kevin")
    )).contextWrite(Context.of(HEADER_AUTH_TOKEN, "eyJhbGciOi"));

    mono.subscribe(data -> log.info("# onNext : {}", data));
  }

  private static Mono<String> postBook(Mono<Book> book) {
    return Mono.zip(
            book, Mono.deferContextual(contextView -> Mono.just(contextView.get(HEADER_AUTH_TOKEN)))
        )
        .flatMap(tuple -> {
          String response = "POST the book(" + tuple.getT1().getBookName() + ", " + tuple.getT1().getAuthor() + ") with token: " + tuple.getT2();
          return Mono.just(response);
        });
  }
}

@Getter
@AllArgsConstructor
class Book {

  private String isbn;
  private String bookName;
  private String author;
}
