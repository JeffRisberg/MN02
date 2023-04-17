package com.example.broker;

import com.example.broker.model.Symbol;
import jakarta.inject.Singleton;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

  private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);

  private final Map<String, Symbol> symbols = new HashMap<>();
  //private final Map<String, Quote> cachedQuotes = new HashMap<>();
  private final Faker faker = new Faker();

  @PostConstruct
  public void initialize() {
    initializeWith(10);
  }

  public void initializeWith(int numberOfEntries) {
    symbols.clear();
    IntStream.range(0, numberOfEntries).forEach(i ->
      addNewSymbol()
    );
  }

  public Map<String, Symbol> getSymbols() {
    return symbols;
  }

  private void addNewSymbol() {
    var symbol = new Symbol(faker.stock().nsdqSymbol());
    symbols.put(symbol.value(), symbol);
    LOG.debug("Added symbol {}", symbol.value());
  }
}
