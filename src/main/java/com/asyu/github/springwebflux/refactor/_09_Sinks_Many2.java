package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class _09_Sinks_Many2 {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> multicastSink = Sinks.many().multicast()
        .onBackpressureBuffer();

    Flux<Integer> fluxView = multicastSink.asFlux();

    multicastSink.emitNext(1, EmitFailureHandler.FAIL_FAST);
    multicastSink.emitNext(2, EmitFailureHandler.FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
    fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

    multicastSink.emitNext(3, EmitFailureHandler.FAIL_FAST);
  }

}
