package com.example.broker;

import com.example.broker.model.Symbol;
import io.micronaut.http.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("/symbols")
public class WatchListController {

  private final InMemoryStore inMemoryStore;

  public WatchListController(final InMemoryStore inMemoryStore) {
    this.inMemoryStore = inMemoryStore;
  }

  @Put(consumes = JsonObject, produces = JsonObject)
  public Watchlist update(Watchlist watchlist) {
    to be filled in

  }
}
