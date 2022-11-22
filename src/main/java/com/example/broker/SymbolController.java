package com.example.broker;

import com.example.broker.model.Symbol;
import io.micronaut.http.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
}
