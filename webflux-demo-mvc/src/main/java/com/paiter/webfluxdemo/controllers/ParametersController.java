package com.paiter.webfluxdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("jobs")
public class ParametersController {

    @GetMapping("search")
    public Flux<Integer> searchJobs(final @RequestParam("count") int count,
                                    final @RequestParam("page") int page) {

        return Flux.just(count, page);
    }
}
