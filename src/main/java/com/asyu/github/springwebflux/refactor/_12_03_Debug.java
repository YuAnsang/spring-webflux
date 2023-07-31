package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class _12_03_Debug {

    public static void main(String[] args) throws InterruptedException {

        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
//                .checkpoint("_12_03_Debug.zipWith.checkpoint")
                .checkpoint("_12_03_Debug.zipWith.checkpoint", true)
                .map(num -> num + 2)
//                .checkpoint("_12_03_Debug.map.checkpoint")
                .checkpoint("_12_03_Debug.map.checkpoint", true)
                .subscribe(
                        data -> log.info("# onNext : {}", data),
                        error -> log.error("# onError : ", error)
                );

        Thread.sleep(200L);
    }

}
