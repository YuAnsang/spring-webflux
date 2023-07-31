package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_GeneralTestExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class _13_06_Test_Publisher {

    @Test
    public void test() {
        // Well-behaved TestPublisher
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(_13_GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.emit(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }

}
