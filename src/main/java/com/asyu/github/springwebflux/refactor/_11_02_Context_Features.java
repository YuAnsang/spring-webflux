package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _11_02_Context_Features {

  public static void main(String[] args) throws InterruptedException {
    String key1 = "company";

    Mono<String> mono = Mono.deferContextual(contextView ->
            Mono.just("Company: " + contextView.get(key1))
        )
        .publishOn(Schedulers.parallel());

    mono.contextWrite(context -> context.put(key1, "Apple"))
        .subscribe(data -> log.info("# subscribe1 onNext : {}", data));

    mono.contextWrite(context -> context.put(key1, "Microsoft"))
      .subscribe(data -> log.info("# subscribe2 onNext : {}", data));

    Thread.sleep(200L);
  }

}
