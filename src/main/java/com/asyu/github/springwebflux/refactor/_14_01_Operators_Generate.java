package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class _14_01_Operators_Generate {

    public static void main(String[] args) throws IOException {
        Flux.generate(() -> 0, (state, sink) -> {
                    sink.next(state); // emit (sync)
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .subscribe(data -> log.info("# onNext : {}", data));
    }
}
