package com.asyu.github.springwebflux.refactor;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
public class _07_HotSequence_with_http {

    public static void main(String[] args) throws InterruptedException {
        URI worldTimeUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build().encode().toUri();

        Mono<String> mono = getWorldTime(worldTimeUri).cache();  // hot source and last emitted signals for further subscriber
        mono.subscribe(datetime -> log.info("# datetime 1 : {}", datetime));
        Thread.sleep(2000L);
        mono.subscribe(datetime -> log.info("# datetime 2 : {}", datetime));
        Thread.sleep(2000L);
    }

    private static Mono<String> getWorldTime(URI worldTimeUri) {
        return WebClient.create()
                .get()
                .uri(worldTimeUri)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    DocumentContext jsonContext = JsonPath.parse(response);
                    return jsonContext.read("$.datetime");
                });
    }
}
