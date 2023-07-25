package com.asyu.github.springwebflux.refactor;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _09_Operator_Signal {

  public static void main(String[] args) throws InterruptedException {
    int tasks = 6;
    Flux.create((FluxSink<String> sink) -> IntStream.range(1, tasks)
        .forEach(n -> sink.next(doTasks(n))))
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext(n -> log.info("# create(): {}", n))
        .publishOn(Schedulers.parallel())
        .map(result -> result + " success!!")
        .doOnNext(n -> log.info("# map(): {}", n))
        .publishOn(Schedulers.parallel())
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(500L);
  }

  private static String doTasks(int taskNumber) {
    return "task " + taskNumber + " result";
  }
}
