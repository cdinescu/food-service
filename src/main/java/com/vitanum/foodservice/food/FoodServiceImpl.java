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

package com.vitanum.foodservice.food;

import com.vitanum.foodservice.http.utils.HttpUtils;
import com.vitanum.foodservice.response.parser.FoodJsonParser;
import com.vitanum.foodservice.uricomponent.builder.UriComponentBuilderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriComponentBuilderUtils uriComponentBuilderUtils;

    @Override
    public List<Food> getFoodByName(String foodSearchKeyword) {
        HttpHeaders headers = HttpUtils.createHttpHeader();

        UriComponentsBuilder builder = uriComponentBuilderUtils.getUriComponentsBuilderForFoodSearch(foodSearchKeyword);
        String body = getResponse(headers, builder);

        return FoodJsonParser.extractFoodFromJson(body);
    }

    @Override
    public Food getFoodNutritionValue(String foodDbNo) {
        HttpHeaders headers = HttpUtils.createHttpHeader();

        UriComponentsBuilder builder = uriComponentBuilderUtils.getUriComponentBuilderForFoodReport(foodDbNo);
        String body = getResponse(headers, builder);

        System.out.print(body);

        return null;
    }

    private String getResponse(HttpHeaders headers, UriComponentsBuilder builder) {
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }
}
