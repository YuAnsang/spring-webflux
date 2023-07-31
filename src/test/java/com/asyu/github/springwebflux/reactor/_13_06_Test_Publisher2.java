package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_GeneralTestExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.List;

public class _13_06_Test_Publisher2 {

    @Test
    public void test() {
        // Misbehaving TestPublisher
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);

        StepVerifier
                .create(_13_GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> {
                    getDataSource().forEach(source::next);
                    source.complete();
                })
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    private static List<Integer> getDataSource() {
        return List.of(2, 4, 6, 8, null);
    }

}
