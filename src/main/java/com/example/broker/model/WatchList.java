package com.example.broker.model;

import io.micronaut.core.annotation.Introspected;

import java.util.ArrayList;
import java.util.List;

@Introspected
public class WatchList {

  private List<Symbol> symbols = new ArrayList<>();

  public WatchList(List<Symbol> symbols) {
    this.symbols = symbols;
  }

  public List<Symbol> getSymbols() {
    return symbols;
  }

  public void setSymbols(List<Symbol> symbols) {
    this.symbols = symbols;
  }
}
