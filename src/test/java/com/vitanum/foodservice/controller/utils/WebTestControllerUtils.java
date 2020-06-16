package com.vitanum.foodservice.controller.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class WebTestControllerUtils {

    private WebTestControllerUtils() {

    }

    public static void checkAuthorizedAccess(WebTestClient client, Function<UriBuilder, URI> uriFunction, Jwt jwt, HttpStatus expectedHttpStatus) {
        client.get()
                .uri(uriFunction)
                .accept(APPLICATION_JSON)
                .headers(addJwt(jwt))
                .exchange()
                .expectStatus().isEqualTo(expectedHttpStatus)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    public static void checkUnAuthorizedAccess(WebTestClient client, Function<UriBuilder, URI> uriFunction) {
        client.get()
                .uri(uriFunction)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED)
                .expectBody();
    }

    private static Consumer<HttpHeaders> addJwt(Jwt jwt) {
        return headers -> headers.setBearerAuth(jwt.getTokenValue());
    }
}
