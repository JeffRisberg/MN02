package com.example.broker;

import com.example.broker.model.Symbol;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("/symbols")
public class SymbolController {

  private final InMemoryStore inMemoryStore;

  public SymbolController(final InMemoryStore inMemoryStore) {
    this.inMemoryStore = inMemoryStore;
  }

  @Get()
  public List<Symbol> getAll() {
    var symbols = inMemoryStore.getSymbols().values();
    return new ArrayList(symbols);
  }

  @Get("{value}")
  public Symbol getSymbolbyValue(@PathVariable String value) {
    return inMemoryStore.getSymbols().get(value);
  }

  @Get("/filter{?max,offset}")
  public List<Symbol> getSymbols(@QueryValue Optional<Integer> max, @QueryValue Optional<Integer> offset) {
    return inMemoryStore.getSymbols().values().stream()
      .skip(offset.orElse(0))
      .limit(max.orElse(0))
      .collect(Collectors.toList());
  }
}
