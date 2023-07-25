package com.asyu.github.springwebflux.refactor;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _09_Sinks {

  public static void main(String[] args) throws InterruptedException {
    int tasks = 6;

    Sinks.Many<String> unicastSink = Sinks.many().unicast()
        .onBackpressureBuffer();

    Flux<String> fluxView = unicastSink.asFlux();
    IntStream.range(1, tasks)
        .forEach(n -> {
          try {
            new Thread(() -> {
              unicastSink.emitNext(doTasks(n), EmitFailureHandler.FAIL_FAST);
              log.info("# emitted: {}", n);
            }).start();
            Thread.sleep(100L);
          } catch (Exception e) {
            log.error(e.getMessage());
          }
        });

    fluxView.publishOn(Schedulers.parallel())
        .map(result -> result + " success!!")
        .doOnNext(n -> log.info(" #map() : {}", n))
        .publishOn(Schedulers.parallel())
        .subscribe(data -> log.info("# onNext : {}", data));

    Thread.sleep(200L);
  }

  private static String doTasks(int taskNumber) {
    return "task " + taskNumber + " result";
  }
}
