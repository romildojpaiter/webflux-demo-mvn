package br.com.paiter.stockquotesapi.populate;

import br.com.paiter.stockquotesapi.entity.Quote;
import br.com.paiter.stockquotesapi.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class QuoteGenerator {

    private final QuoteRepository quoteRepository;
    private final ConnectionFactoryInitializer connectionFactoryInitializer;

    @PostConstruct
    public void init() {
        quoteRepository.save(Quote.builder()
                .symbol("TESTE")
                .openValue(0.222222)
                .closeValue(0.222222)
                .timestamp(LocalDateTime.now())
                .build())
        .subscribe(
                e -> quoteRepository.findAll().subscribe(
                        q -> log.info("quote {}", q),
                        ex -> log.error("error {}", ex.getMessage())
                )
        );

    }
}
