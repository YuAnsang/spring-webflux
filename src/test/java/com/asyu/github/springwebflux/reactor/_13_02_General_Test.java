package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_GeneralTestExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class _13_02_General_Test {

    @Test
    public void test_say_hello() {
        StepVerifier
                .create(_13_GeneralTestExample.sayHello())
                .expectSubscription()
                .as("# expect subscription")
//                .expectNext("Hi") // failed
                .expectNext("Hello") // success
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

    @Test
    public void test_divide_two() {
        // Given
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);

        // When & Then
        StepVerifier
                .create(_13_GeneralTestExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError()
                .verify();
    }

    @Test
    public void test_take_number() {
        // Given
        Flux<Integer> source = Flux.range(0, 1000);

        // When & Then
        StepVerifier
                .create(
                        _13_GeneralTestExample.takeNumber(source, 500),
                        StepVerifierOptions.create().scenarioName("Verify from 0 to 499")
                )
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)
//                .expectNext(500) // failed
                .expectNext(499) // success
                .expectComplete()
                .verify();
    }
}
