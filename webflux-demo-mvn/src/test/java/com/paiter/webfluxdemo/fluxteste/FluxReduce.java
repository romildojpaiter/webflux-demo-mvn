package com.paiter.webfluxdemo.fluxteste;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxReduce {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .reduce(0, Integer::sum)
                .subscribe(e -> log.info("soma total: {}", e),
                        error -> log.error("error " + error),
                        () -> log.info("complete"));
    }
}
