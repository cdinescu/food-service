package com.vitanum.foodservice.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.vitanum.foodservice.food.Food;
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

import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Value("${usda.search.url}")
    private String foodSearchServiceURL;

    @Value("${api.key}")
    private String foodServiceApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/search")
    public void getFoodByGeneralSearchInput(@RequestParam String searchInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-api-key", foodServiceApiKey);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL).queryParam("generalSearchInput", searchInput);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String body = response.getBody();
        System.out.println(body);

        try {
            for (Iterator it = new ObjectMapper().readValues(
                    new JsonFactory().createJsonParser(body), Map.class); it.hasNext();)
                {
                    Map next = (Map) it.next();

                   // List<String> list = (List<String>)next.get("foods");
                    //System.out.println("Uite " + list);

                    //for(String f : list) {
                   //     System.out.println("My Food: " + f);
                   // }
                }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
