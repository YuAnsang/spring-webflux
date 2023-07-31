package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class _12_02_Debug {

    public static void main(String[] args) throws InterruptedException {

        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint()
                .map(num -> num + 2)
                .checkpoint()
                .subscribe(
                        data -> log.info("# onNext : {}", data),
                        error -> log.error("# onError : ", error)
                );

        Thread.sleep(200L);
    }

}
