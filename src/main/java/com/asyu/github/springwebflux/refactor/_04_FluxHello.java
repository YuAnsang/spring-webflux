package com.asyu.github.springwebflux.refactor;

import reactor.core.publisher.Flux;

public class _04_FluxHello {

    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto")
        ).collectList()
        .subscribe(planets -> System.out.println("planets = " + planets));
    }

}
