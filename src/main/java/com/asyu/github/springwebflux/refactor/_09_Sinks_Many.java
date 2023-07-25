package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class _09_Sinks_Many {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> unicastSink = Sinks.many().unicast()
        .onBackpressureBuffer();

    Flux<Integer> fluxView = unicastSink.asFlux();

    unicastSink.emitNext(1, EmitFailureHandler.FAIL_FAST);
    unicastSink.emitNext(2, EmitFailureHandler.FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

    unicastSink.emitNext(3, EmitFailureHandler.FAIL_FAST);

    // unicast() sinks only allow a single Subscriber
    //  fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
  }

}
