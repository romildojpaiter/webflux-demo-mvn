package com.paiter.webfluxdemo.fluxteste;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxConcat {

    public static void main(String[] args) {
        Flux.concat(
                Mono.just(1),
                Mono.just(2)
        ).doOnNext(System.out::println)
        .reduce(0, Integer::sum)
        .subscribe(
                e -> log.info("soma total: {}", e),
                error -> log.error("error " + error),
                () -> log.info("complete")
        );
    }
}
