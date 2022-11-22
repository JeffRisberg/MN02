package com.example.broker;

import com.example.broker.model.Symbol;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.ArrayList;
import java.util.List;

@Controller("/symbols")
public class SymbolController {

  private final InMemoryStore store;

  public SymbolController(final InMemoryStore store) {
    this.store = store;
  }

  @Get()
  public List<Symbol> getAll() {
    var symbols = store.getSymbols().values();
    return new ArrayList(symbols);
  }
}
