package br.com.paiter.stockquotesapi.handler;

import br.com.paiter.stockquotesapi.entity.Quote;
import br.com.paiter.stockquotesapi.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class QuoteHandler {

    private final QuoteService quoteService;

    public Mono<ServerResponse> getAllQuotes(ServerRequest request) {
        var quotes = quoteService.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(quotes, Quote.class);
    }
}
