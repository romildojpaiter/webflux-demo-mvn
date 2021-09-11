package br.com.paiter.stockquotesapi.repository;

import br.com.paiter.stockquotesapi.entity.Quote;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends ReactiveCrudRepository<Quote, Long> {

    //@RestResource(rel = "quotes", path = "quotes")
    List<Quote> findAllBySymbol(@Param("symbol") String symbol);

    Optional<Quote> findFirstBySymbolOrderByTimestampDesc(String symbol);
}
