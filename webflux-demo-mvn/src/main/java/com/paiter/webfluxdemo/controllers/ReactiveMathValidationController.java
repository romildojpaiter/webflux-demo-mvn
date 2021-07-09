package com.paiter.webfluxdemo.controllers;

import com.paiter.webfluxdemo.dto.Response;
import com.paiter.webfluxdemo.exception.InputValidationException;
import com.paiter.webfluxdemo.services.ReactiveMatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@AllArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMatchService reactiveMatchService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }

        return this.reactiveMatchService.findSquare(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable("input") int valueInput) {
        return Mono.just(valueInput)
                .handle((valueInteger, outputSink) -> {
                    if (valueInteger >=10 && valueInteger <=20) {
                        outputSink.next(valueInteger);
                    } else {
                        outputSink.error(new InputValidationException(valueInteger));
                    }
                })
                .cast(Integer.class)
                .flatMap(this.reactiveMatchService::findSquare);
    }

    @GetMapping("square/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(this.reactiveMatchService::findSquare)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
