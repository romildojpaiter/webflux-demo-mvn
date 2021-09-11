package br.com.paiter.stockquotesapi.populate;

//@Log4j2
//@Configuration
//@EnableScheduling
//@RequiredArgsConstructor
public class PopulateQuotes {

//    private final QuoteRepository quoteRepository;

//    @Scheduled(fixedDelay = 1000)
//    public void generateData() {
//        log.info(quoteRepository.findFirstBySymbolOrderByTimestampDesc("TESTE").map(this::generateNewData).orElseGet(this::initilizeData));
//    }
//
//    private Quote initilizeData() {
//        return quoteRepository.save(Quote.builder()
//                .symbol("TESTE")
//                .openValue(0.222222)
//                .closeValue(0.222222)
//                .timestamp(LocalDateTime.now())
//                .build());
//    }
//
//    private Quote generateNewData(Quote quote) {
//        return quoteRepository.save(Quote.builder()
//                .symbol(quote.getSymbol())
//                .openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.010, 0.10))
//                .closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.010, 0.10))
//                .timestamp(LocalDateTime.now())
//                .build());
//    }
}
