package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _11_01_Context_Basic {

  public static void main(String[] args) throws InterruptedException {
    Mono
        .deferContextual(contextView ->
            Mono
                .just("Hello " + contextView.get("firstName"))
                .doOnNext(data -> log.info("# just doOnNext : {}", data))
        )
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel())
        .transformDeferredContextual(
            (mono, context) -> mono.map(data -> data + " " + context.get("lastName"))
        )
        .contextWrite(context -> context.put("lastName", "Jobs"))
        .contextWrite(context -> context.put("firstName", "Steve"))
        .subscribe(data -> log.info("# onNext : {}", data));

    Thread.sleep(200L);
  }

}
