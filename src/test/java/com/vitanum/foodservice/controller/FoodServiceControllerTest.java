package com.vitanum.foodservice.controller;

import com.vitanum.foodservice.constants.Constants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
public class FoodServiceControllerTest implements Constants {

    public static final String FOOD_SEARCH_KEYWORD = "foodSearchKeyword";

    @Autowired
    private WebTestClient client;

    @Test
    public void testGetFoodByGeneralSearchInput() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_SEARCH_URL)
                        .queryParam(FOOD_SEARCH_KEYWORD, "banana").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testGetFoodByGeneralSearchInputMultipleTokens() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_SEARCH_URL)
                        .queryParam(FOOD_SEARCH_KEYWORD, "Candies, dark chocolate coated coffee beans").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testGetFoodByGeneralSearchInputWhenFoodNotFound() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_SEARCH_URL)
                        .queryParam(FOOD_SEARCH_KEYWORD, "heeeeeeeeeeeeey").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testGetFoodByGeneralSearchInputWhenQueryParamEmpty() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_SEARCH_URL)
                        .queryParam(FOOD_SEARCH_KEYWORD, "").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testGetFoodNutrients() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_REPORTS_URL + "/" + FOOD_ID)
                        .build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testGetFoodNutrientsWhenFoodIndexHasWrongFormat() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_REPORTS_URL + "/random")
                        .build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody();
    }
}
