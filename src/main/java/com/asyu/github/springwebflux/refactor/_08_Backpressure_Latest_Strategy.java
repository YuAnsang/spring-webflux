package com.asyu.github.springwebflux.refactor;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _08_Backpressure_Latest_Strategy {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureLatest()
        .publishOn(Schedulers.parallel())
        .subscribe(data -> {
          try {
            Thread.sleep(5L);
          } catch (InterruptedException e) {
          }
          log.info("# onNext: {}", data);
        }, throwable -> log.error("# onError"));

    Thread.sleep(2000L);
  }

}
