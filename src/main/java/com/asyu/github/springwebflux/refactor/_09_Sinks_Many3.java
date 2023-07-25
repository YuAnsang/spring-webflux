package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class _09_Sinks_Many3 {

  public static void main(String[] args) throws InterruptedException {
    Many<Integer> replaySink = Sinks.many().replay().limit(2);

    Flux<Integer> fluxView = replaySink.asFlux();

    replaySink.emitNext(1, EmitFailureHandler.FAIL_FAST);
    replaySink.emitNext(2, EmitFailureHandler.FAIL_FAST);
    replaySink.emitNext(3, EmitFailureHandler.FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

    replaySink.emitNext(4, EmitFailureHandler.FAIL_FAST);

    fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
  }

}
