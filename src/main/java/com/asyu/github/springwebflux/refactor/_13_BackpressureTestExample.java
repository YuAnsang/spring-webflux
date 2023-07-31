package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
public class _13_BackpressureTestExample {

    public static Flux<Integer> generateNumber() {
        return Flux.create(emitter -> {
            for (int i = 1; i <= 100; i++) {
                emitter.next(i);
            }
            emitter.complete();
        }, FluxSink.OverflowStrategy.ERROR);
    }

}
