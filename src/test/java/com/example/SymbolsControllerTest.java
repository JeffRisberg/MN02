package com.example;

import static io.micronaut.http.HttpRequest.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.broker.model.Symbol;
import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

@MicronautTest
class SymbolsControllerTest {

  private static final Logger LOG = LoggerFactory.getLogger(SymbolsControllerTest.class);

  @Inject
  EmbeddedApplication<?> application;

  @Inject
  @Client("/symbols")
  HttpClient client;

  @Test
  void testItWorks() {
    Assertions.assertTrue(application.isRunning());
  }

  @Test
  void symbolsEndpointReturnsListofSymbols() {
    var response = client.toBlocking().exchange("/", JsonNode.class);
    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(10, response.getBody().get().size());
  }

  //@Test
  //void returnsQuotePerSymbol() {
  //  final Symbol appleResult = client.toBlocking().retrieve(GET("/symbols/APPL"), Symbol.class);
  //  LOG.info("Result: {}", appleResult);
  //  //assertThat(apple).usingRecursiveComparison().isEqualTo(appleResult);
  //}
}
