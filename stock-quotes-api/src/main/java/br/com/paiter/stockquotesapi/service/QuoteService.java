package br.com.paiter.stockquotesapi.service;

import br.com.paiter.stockquotesapi.entity.Quote;
import br.com.paiter.stockquotesapi.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository repository;

    public Flux<Quote> findAll() {
        return repository.findAll();
    }
}
