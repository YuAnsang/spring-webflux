package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_BackpressureTestExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class _13_04_Backpressure_Test {

    @Test
    public void test() {
        StepVerifier
                .create(_13_BackpressureTestExample.generateNumber()) // success
//                .create(_13_BackpressureTestExample.generateNumber(), 1L) // failed
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }

    @Test
    public void test2() {
        StepVerifier
                .create(_13_BackpressureTestExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat()
                .hasDroppedElements()
        ;
    }
}
