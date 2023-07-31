package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Slf4j
public class _12_04_Debug {

    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log("Fruit.Substring", Level.INFO)
                .map(fruits::get)
//                .map(translated -> "맛있는 " + translated)
                .subscribe(
                        log::info,
                        error -> log.error("# onError: ", error
                        )
                );

        Thread.sleep(200L);
    }

}
