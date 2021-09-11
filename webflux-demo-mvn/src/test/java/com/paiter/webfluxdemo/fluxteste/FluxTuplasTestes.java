package com.paiter.webfluxdemo.fluxteste;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Slf4j
public class FluxTuplasTestes {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .timestamp()
                .index()
                .subscribe(
                        e -> log.info("index {}, time {}, Date {}",
                                e.getT1(),
                                Instant.ofEpochMilli(e.getT2().getT1()),
                                e.getT2().getT2()),
                        error -> log.error("error " + error),
                        () -> log.info("complete"));
    }
}
