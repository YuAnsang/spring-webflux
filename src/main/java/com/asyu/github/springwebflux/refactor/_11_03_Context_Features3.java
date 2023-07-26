package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _11_03_Context_Features3 {

  public static void main(String[] args) throws InterruptedException {
    String key1 = "company";

    Mono.just("Steve")
//        .transformDeferredContextual(((stringMono, contextView) -> contextView.get("role")))
        .flatMap(name ->
            Mono.deferContextual(contextView ->
                    Mono.just(contextView.get(key1) + ", " + name)
                        .transformDeferredContextual((mono, innerContextview) ->
                            mono.map(data -> data + ", " + innerContextview.get("role")))
                )
                .contextWrite(context -> context.put("role", "CEO"))
        )
        .publishOn(Schedulers.parallel())
        .contextWrite(context -> context.put(key1, "Apple"))
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(200L);
  }

}
