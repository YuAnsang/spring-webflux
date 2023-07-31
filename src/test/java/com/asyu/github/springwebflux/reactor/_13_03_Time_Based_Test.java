package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_TimeBasedTestExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class _13_03_Time_Based_Test {

    @Test
    public void test_time_based() {
        StepVerifier
                .withVirtualTime(() -> _13_TimeBasedTestExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(1)).take(1)
                ))
                .expectSubscription()
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(1)))
                .expectNextCount(11)
                .expectComplete()
                .verify()
        ;
    }

    @Test
    public void test_time_based2() {
        StepVerifier
                .withVirtualTime(() -> _13_TimeBasedTestExample.getCOVID19Count(
                        Flux.interval(Duration.ofMinutes(1)).take(1)
                ))
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3)) // failed
        ;
    }

    @Test
    public void test_time_based3() {
        StepVerifier
                .withVirtualTime(() -> _13_TimeBasedTestExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify()
        ;
    }
}
