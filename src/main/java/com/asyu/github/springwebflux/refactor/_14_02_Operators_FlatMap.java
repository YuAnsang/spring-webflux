package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class _14_02_Operators_FlatMap {

    public static void main(String[] args) throws IOException {
        Flux.just("Good", "Bad")
                .flatMap(feeling -> Flux.just("Morning", "Afternoon", "Evening").map(time -> feeling + " " + time))
                .subscribe(log::info);
    }
}
