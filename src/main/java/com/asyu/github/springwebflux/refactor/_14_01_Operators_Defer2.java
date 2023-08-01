package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class _14_01_Operators_Defer2 {

    public static void main(String[] args) throws InterruptedException {
        log.info("# start : {}", LocalDateTime.now());
        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(3))
//                .switchIfEmpty(sayDefault())
                .switchIfEmpty(Mono.defer(_14_01_Operators_Defer2::sayDefault))
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(3_500L);
    }

    private static Mono<String> sayDefault() {
        log.info("# Say Hi");
        return Mono.just("Hi");
    }
}
