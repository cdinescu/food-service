package com.vitanum.foodservice.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/foods")
public class FoodController {

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

    @GetMapping("/search")
    public void getFoodByGeneralSearchInput(@RequestParam String searchInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL)
                        .queryParam("format", responseFormat)
                        .queryParam("q", searchInput)
                        .queryParam("max", maxResultsPerQuery)
                        .queryParam("offset", 0)
                        .queryParam("api_key", foodServiceApiKey);


        System.out.println(builder.toUriString());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String body = response.getBody();
        System.out.println(body);

        try {
            MappingIterator<Map> mapMappingIterator = new ObjectMapper().readValues(
                    new JsonFactory().createParser(body), Map.class);
            for (Iterator it = mapMappingIterator; it.hasNext(); ) {
                Map next = (Map) it.next();

                Map<String, Object> list = (Map<String, Object>) next.get("list");
                System.out.println("...................... " + list.get("item"));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
