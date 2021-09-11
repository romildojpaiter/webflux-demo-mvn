package com.paiter.webfluxdemo.fluxteste;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxFilter {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i % 2 == 0)
                .map(v -> v * 2)
                .subscribe(e -> log.info("elem: {}", e),
                        error -> log.error("error " + error),
                        () -> log.info("complete"));

    }
}
