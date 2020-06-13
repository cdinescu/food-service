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

import com.vitanum.foodservice.controller.utils.WebTestControllerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

import static com.vitanum.foodservice.constants.Constants.FOOD_ID;
import static com.vitanum.foodservice.constants.Constants.FOOD_REPORTS_URL;
import static com.vitanum.foodservice.constants.Constants.FOOD_SEARCH_URL;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodServiceControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private ReactiveJwtDecoder jwtDecoder;

    private final Jwt myJwt = jwt();

    @BeforeEach
    void setUp() {
        when(this.jwtDecoder.decode(anyString())).thenReturn(Mono.just(this.myJwt));
    }

    @Test
    public void testGetFoodByGeneralSearchInput() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_SEARCH_URL)
                .queryParam("foodSearchKeyword", "banana").build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.OK);
    }

    @Test
    public void testRequestWithoutToken() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_SEARCH_URL)
                .queryParam("foodSearchKeyword", "banana").build();

        // Act & Assert
        WebTestControllerUtils.checkUnAuthorizedAccess(client, uriFunction, myJwt);
    }

    @Test
    public void testGetFoodByGeneralSearchInputMultipleTokens() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_SEARCH_URL)
                .queryParam("foodSearchKeyword", "Candies, dark chocolate coated coffee beans").build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.OK);
    }

    @Test
    public void testGetFoodByGeneralSearchInputWhenFoodNotFound() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_SEARCH_URL)
                .queryParam("foodSearchKeyword", "heeeeeeeeeeeeey").build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetFoodByGeneralSearchInputWhenQueryParamEmpty() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_SEARCH_URL)
                .queryParam("foodSearchKeyword", "").build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetFoodNutrients() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_REPORTS_URL + "/" + FOOD_ID)
                .build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.OK);
    }

    @Test
    public void testGetFoodNutrientsWhenFoodIndexHasWrongFormat() {
        // Arrange
        Function<UriBuilder, URI> uriFunction = uriBuilder -> uriBuilder
                .path(FOOD_REPORTS_URL + "/random")
                .build();

        // Act & Assert
        WebTestControllerUtils.checkAuthorizedAccess(client, uriFunction, myJwt, HttpStatus.NOT_FOUND);
    }

    private Jwt jwt() {
        return new Jwt("token", null, null,
                Map.of("alg", "none"), Map.of("sub", "cristina"));
    }
}
