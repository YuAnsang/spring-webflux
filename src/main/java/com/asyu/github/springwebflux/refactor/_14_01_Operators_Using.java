package com.asyu.github.springwebflux.refactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class _14_01_Operators_Using {

    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("txt/using_example.txt");
        Path path = Paths.get(resource.getURI());

        Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
                .subscribe(log::info);
    }
}
