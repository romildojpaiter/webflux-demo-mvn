package br.com.paiter.stockquotesapi.controller;

import br.com.paiter.stockquotesapi.entity.Quote;
import br.com.paiter.stockquotesapi.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RequestMapping("v1/quotes")
public record QuoteController(QuoteService service) {

    @GetMapping
    public Flux<Quote> findAll() {
        return service.findAll();
    }

}
