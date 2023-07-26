package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _11_03_Context_Features2 {

  public static void main(String[] args) throws InterruptedException {
    String key1 = "company";
    String key2 = "name";

    Mono.deferContextual(contextView ->
            Mono.just(contextView.get(key1))
        )
        .publishOn(Schedulers.parallel())
        .contextWrite(context -> context.put(key2, "Bill"))
        .transformDeferredContextual((mono, contextView2) -> mono.map(data -> data + ", " + contextView2.getOrDefault(key2, "Steve")))
        .contextWrite(context -> context.put(key1, "Apple"))
        .subscribe(data -> log.info("# subscribe1 onNext : {}", data));

    Thread.sleep(200L);
  }

}
