package com.asyu.github.springwebflux.reactor;

import com.asyu.github.springwebflux.refactor._13_RecordBasedTestExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class _13_05_Record_Based_Test {

    @Test
    public void test() {
        StepVerifier
                .create(_13_RecordBasedTestExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india"))
                )
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    assertThat(countries.stream().allMatch(country -> Character.isUpperCase(country.charAt(0)))).isTrue();
                })
                .expectComplete()
                .verify();
    }

}
