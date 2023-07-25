package com.asyu.github.springwebflux.refactor;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.net.URI;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class _08_Backpressure_count {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
            .doOnRequest(data -> log.info("doOnRequest : {}", data))
            .subscribe(new BaseSubscriber<>() {

              @Override
              protected void hookOnSubscribe(Subscription subscription) {
                request(1);
              }

              @SneakyThrows
              @Override
              protected void hookOnNext(Integer value) {
                Thread.sleep(500L);
                log.info("hookOnNext : {}", value);
                request(1);
              }
            });
    }

}
