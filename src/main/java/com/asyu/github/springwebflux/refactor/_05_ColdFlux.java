package com.asyu.github.springwebflux.refactor;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class _05_ColdFlux {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .map(String::toLowerCase);

        coldFlux.subscribe(country -> System.out.println("subscriber1 = " + country));
        System.out.println("------------------------------");
        Thread.sleep(1000L);
        coldFlux.subscribe(country -> System.out.println("subscriber2 = " + country));
    }

}
