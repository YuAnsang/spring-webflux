package com.asyu.github.springwebflux.refactor;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _08_Backpressure_Buffer_Strategy {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofMillis(300L))
        .doOnNext(data -> log.info("emitted by original Flux : {}", data))
        .onBackpressureBuffer(
            2,
            dropped -> log.info("** Overflow & Dropped : {} **", dropped),
//            BufferOverflowStrategy.DROP_LATEST
            BufferOverflowStrategy.DROP_OLDEST
        )
        .doOnNext(data -> log.info("[ emitted by Buffer : {} ]", data))
        .publishOn(Schedulers.parallel(), false, 1)
        .subscribe(data -> {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException e) {
          }
          log.info("# onNext: {}", data);
        }, throwable -> log.error("# onError"));

    Thread.sleep(3000L);
  }

}
