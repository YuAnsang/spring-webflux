package com.asyu.github.springwebflux.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class _13_01_Signal_Event_Test {

    @Test
    public void test_signal_event() {
        StepVerifier
                .create(Mono.just("Hello Reactor")) // 테스트 대상 Sequence 생성
                .expectNext("Hello Reactor") // emit 된 데이터 expect
                .expectComplete() // onComplete Signal expect
                .verify(); // 검증 실행
    }
}
