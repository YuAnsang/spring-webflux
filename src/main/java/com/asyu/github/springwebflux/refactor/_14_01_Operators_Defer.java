package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class _14_01_Operators_Defer {

    public static void main(String[] args) throws InterruptedException {
        log.info("# start : {}", LocalDateTime.now());
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2_000L);

        justMono.subscribe(data -> log.info("# onNext just1: {}", data));
        deferMono.subscribe(data -> log.info("# onNext just1: {}", data));

        Thread.sleep(2_000L);

        justMono.subscribe(data -> log.info("# onNext just2: {}", data));
        deferMono.subscribe(data -> log.info("# onNext just2: {}", data));
    }
}
