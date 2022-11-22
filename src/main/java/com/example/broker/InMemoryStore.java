package com.example.broker;

import com.example.broker.model.Quote;
import com.example.broker.model.Symbol;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.*;

import jakarta.inject.Singleton;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class InMemoryStore {

  private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);

  private final Map<String, Symbol> symbols = new HashMap<>();
  //private final Map<String, Quote> cachedQuotes = new HashMap<>();
  private final Faker faker = new Faker();

  @PostConstruct
  public void initialize() {
    IntStream.range(0, 10).forEach(i ->
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