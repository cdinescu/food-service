package com.vitanum.foodservice.controller;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
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
import java.net.InetSocketAddress;

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

        System.out.println(response.getBody());
    }

}
