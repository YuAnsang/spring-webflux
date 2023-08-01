package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class _14_04_Operators_Window {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 11)
                .window(3)
                .flatMap(flux -> {
                    log.info("===========================");
                    return flux;
                })
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# onNext : {}", value);
                        request(2);
                    }
                });

        Thread.sleep(100L);
    }
}
