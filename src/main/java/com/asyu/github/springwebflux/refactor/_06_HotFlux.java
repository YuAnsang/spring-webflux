package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class _06_HotFlux {

    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D", "Singer E"};

        log.info("=== start ===");
        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1)) // emit을 일정시간동안 지연
                .share(); // Hot Sequence로 동작

        concertFlux.subscribe(singer -> log.info("#subscriber1. singer : " + singer));
        Thread.sleep(2200L);
        concertFlux.subscribe(singer -> log.info("#subscriber2. singer : " + singer));
        Thread.sleep(3000L);
    }

}
