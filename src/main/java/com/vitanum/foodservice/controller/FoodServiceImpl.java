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

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.Nutrient;
import com.vitanum.foodservice.exeptions.ImproperRequestException;
import com.vitanum.foodservice.http.utils.HttpUtils;
import com.vitanum.foodservice.response.parser.FoodJsonParser;
import com.vitanum.foodservice.utils.UriComponentBuilderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriComponentBuilderUtils uriComponentBuilderUtils;

    @Override
    public List<Food> getFoodByName(String foodSearchKeyword) throws ImproperRequestException {
        // sanitize request parameter
        sanitizeRequestParameter(foodSearchKeyword);

        // set-up the HTTP header and URI builder
        HttpHeaders headers = HttpUtils.createHttpHeader();
        UriComponentsBuilder builder = uriComponentBuilderUtils.getUriComponentsBuilderForFoodSearch(foodSearchKeyword);

        // return the food entities extracted from the response
        return FoodJsonParser.extractFoodFromJson(getResponse(headers, builder));
    }

    @Override
    public List<Nutrient> getFoodNutritionValue(String ndbNo) throws ImproperRequestException {
        // sanitize request parameter
        sanitizeRequestParameter(ndbNo);

        // set-up the HTTP header and URI builder
        HttpHeaders headers = HttpUtils.createHttpHeader();
        UriComponentsBuilder builder = uriComponentBuilderUtils.getUriComponentBuilderForFoodReport(ndbNo);

        // return the nutrients extracted from the response
        return FoodJsonParser.extractNutrientsFromJson(getResponse(headers, builder));
    }

    private void sanitizeRequestParameter(String requestParameter) throws ImproperRequestException {
        if (requestParameter == null || requestParameter.isEmpty()) {
            throw new ImproperRequestException("Failed to send request: improper parameter");
        }
    }

    private ResponseEntity<String> getResponse(HttpHeaders headers, UriComponentsBuilder builder) {
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class);

        } catch (HttpStatusCodeException theException) {
            return ResponseEntity.status(theException.getRawStatusCode()).headers(theException.getResponseHeaders())
                    .body(theException.getResponseBodyAsString());
        }
    }
}
