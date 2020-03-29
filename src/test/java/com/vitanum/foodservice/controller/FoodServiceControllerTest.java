/*
 * 	Copyright 2020 Cristina Dinescu
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 *
 */

package com.vitanum.foodservice.controller;

import com.vitanum.foodservice.constants.Constants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodServiceControllerTest implements Constants {

    @Autowired
    private WebTestClient client;

    @Test
    public void testGetFoodByGeneralSearchInput() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FOOD_SEARCH_URL)
                        .queryParam("foodSearchKeyword", "banana").build())
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
                        .queryParam("foodSearchKeyword", "Candies, dark chocolate coated coffee beans").build())
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
                        .queryParam("foodSearchKeyword", "heeeeeeeeeeeeey").build())
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
                        .queryParam("foodSearchKeyword", "").build())
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
