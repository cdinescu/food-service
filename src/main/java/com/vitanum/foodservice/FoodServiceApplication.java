package com.vitanum.foodservice;

import com.vitanum.foodservice.controller.FoodService;
import com.vitanum.foodservice.controller.FoodServiceImpl;
import com.vitanum.foodservice.utils.UriComponentBuilderUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodServiceApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FoodService foodService() {
        return new FoodServiceImpl();
    }

    @Bean
    public UriComponentBuilderUtils uriBuilder() {
        return new UriComponentBuilderUtils();
    }

    public static void main(String[] args) {
        SpringApplication.run(FoodServiceApplication.class, args);
    }
}
