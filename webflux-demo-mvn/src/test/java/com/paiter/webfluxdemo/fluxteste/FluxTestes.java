package com.paiter.webfluxdemo.fluxteste;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxTestes {

    public static void main(String[] args) {
        var somentePares = false;
        Flux.defer(() -> somentePares ?
                Flux.range(1, 10).filter(p -> p % 2 == 0) :
                Flux.range(1, 10).filter(p -> p % 2 == 1)
        ).subscribe(
                e -> log.info("elem: {}", e),
                error -> log.error("error " + error),
                () -> log.info("complete")
//                ,subscriptor -> {
//                    subscriptor.request(4);
//                }
        );
    }
}
