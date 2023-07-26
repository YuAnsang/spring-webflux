package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _10_02_Publish_On {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromArray(new Integer[]{1, 3, 5, 7})
        .doOnNext(data -> log.info("# doOnNext : {}", data)) // subscribeOn을 사용하지 않았기 때문에 여전히 main 스레드에서 실행
        .doOnSubscribe(subscription -> log.info("# doOnSubscribe!"))
        .publishOn(Schedulers.parallel()) // Downstream으로 Signal을 전송 할 때, 실행되는 스레드를 제어하는 역할.
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(500L);
  }

}
