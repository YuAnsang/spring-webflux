package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _10_05_Only_Use_Publish_On_Operator {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromArray(new Integer[]{1, 3, 5, 7, 9})
        .doOnNext(data -> log.info("# doOnNext FromArray : {}", data))
        .publishOn(Schedulers.parallel())
        .filter(data -> data > 3)
        .doOnNext(data -> log.info("# doOnNext filter : {}", data))
        .publishOn(Schedulers.parallel())
        .map(data -> data * 10)
        .doOnNext(data -> log.info("# doOnNext map(): {}", data))
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(100L);
  }

}
