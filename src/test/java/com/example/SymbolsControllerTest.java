package com.example;

import static io.micronaut.http.HttpRequest.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.broker.InMemoryStore;
import com.example.broker.model.Symbol;
import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
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

  @Inject
  InMemoryStore inMemoryStore;

  @BeforeEach
  void setup() {
    inMemoryStore.initializeWith(12);
  }

  @Test
  void symbolsEndpointReturnsListOfSymbols() {
    var response = client.toBlocking().exchange("/", JsonNode.class);
    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(12, response.getBody().get().size());
  }

  @Test
  void symbolsEndpointReturnsCorrectSymbol() {
    var testSymbol = new Symbol("TEST");
    inMemoryStore.getSymbols().put(testSymbol.value(), testSymbol);

    var response = client.toBlocking().exchange("/"+ testSymbol.value(), Symbol.class);
    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(testSymbol, response.getBody().get());
  }

  @Test
  void symbolsEndpointReturnsFilteredList() {
    var response = client.toBlocking().exchange("/filter?max=3", JsonNode.class);
    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(3, response.getBody().get().size());
  }
}
