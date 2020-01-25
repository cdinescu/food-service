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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FoodServiceImpl implements FoodService {
    @Value("${usda.search.url}")
    private String foodSearchServiceURL;

    @Value("${api.key}")
    private String foodServiceApiKey;

    @Value("${max.results.per.query}")
    private Integer maxResultsPerQuery;

    @Value("${response.format}")
    private String responseFormat;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Food> getFoodByName(String foodSearchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = getUriComponentsBuilder(foodSearchKeyword);
        String body = getResponse(headers, builder);

        return extractFoodFromJson(body);
    }

    private UriComponentsBuilder getUriComponentsBuilder(String foodSearchKeyword) {
        return UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL + "/search")
                .queryParam("format", responseFormat)
                .queryParam("q", foodSearchKeyword)
                .queryParam("max", maxResultsPerQuery)
                .queryParam("offset", 0)
                .queryParam("api_key", foodServiceApiKey);
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

    private List<Food> extractFoodFromJson(String body) {
        List<Food> result = new ArrayList<>();

        try {
            MappingIterator<Map> mapMappingIterator = new ObjectMapper().readValues(
                    new JsonFactory().createParser(body), Map.class);
            for (Iterator it = mapMappingIterator; it.hasNext(); ) {
                Map next = (Map) it.next();
                Map<String, Object> list = (Map<String, Object>) next.get("list");

                fetchFoodFromItems(result, list);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private void fetchFoodFromItems(List<Food> result, Map<String, Object> list) {
        List<Map<String, String>> item = (List<Map<String, String>>) list.get("item");

        for (Map<String, String> map : item) {
            String foodName = map.get("name");
            String ndbno = map.get("ndbno");
            // System.out.println(foodName + "--- " + ndbno);
            result.add(new Food(foodName, ndbno));
        }
    }

    @Override
    public Food getFoodNutritionValue(String foodDbNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL + "/reports")
                .queryParam("format", responseFormat)
                .queryParam("ndbno", foodDbNo)
                .queryParam("api_key", foodServiceApiKey);
        String body = getResponse(headers, builder);

        System.out.print(body);

        return null;
    }
}
