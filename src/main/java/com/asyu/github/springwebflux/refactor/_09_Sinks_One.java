package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.One;

@Slf4j
public class _09_Sinks_One {

  public static void main(String[] args) throws InterruptedException {
    One<String> sinkOne = Sinks.one();
    Mono<String> mono = sinkOne.asMono();

    sinkOne.emitValue("Hello Reactor", EmitFailureHandler.FAIL_FAST);
//    sinkOne.emitValue("Hi Reactor", EmitFailureHandler.FAIL_FAST);

    mono.subscribe(data -> log.info("# data : {}", data));
    mono.subscribe(data -> log.info("# data : {}", data));
  }

}
