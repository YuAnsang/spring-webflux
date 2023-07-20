package com.asyu.github.springwebflux.refactor;

import reactor.core.publisher.Flux;

public class _01_Hello {

    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(String::toLowerCase)
                .subscribe(data -> System.out.println("data = " + data));
    }

}
