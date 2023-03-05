package com.example.broker;


import com.example.broker.model.WatchList;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Controller("/account/watchlist")
public class WatchListController {

  private static final Logger LOG = LoggerFactory.getLogger(WatchListController.class);

  static final UUID ACCOUNT_ID = UUID.randomUUID();

  public WatchListController() {
    //this.store = store;
  }

  @Get(produces = MediaType.APPLICATION_JSON)
  public WatchList get() {
    LOG.debug("get - {}", Thread.currentThread().getName());
    //return store.getWatchList(ACCOUNT_ID);
    return null;
  }

  @Put(
    consumes = MediaType.APPLICATION_JSON,
    produces = MediaType.APPLICATION_JSON
  )
  public WatchList update(@Body WatchList watchList) {
    //return store.updateWatchList(ACCOUNT_ID, watchList);
    return null;
  }

  @Delete(
    value = "/{accountId}",
    consumes = MediaType.APPLICATION_JSON,
    produces = MediaType.APPLICATION_JSON
  )
  public void delete(@PathVariable UUID accountId) {
    //store.deleteWatchList(accountId);
  }
}
