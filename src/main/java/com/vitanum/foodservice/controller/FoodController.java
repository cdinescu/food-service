package com.vitanum.foodservice.controller;

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.exceptions.ImproperRequestException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foods")
@Slf4j
public class FoodController {
    @Autowired
    private FoodService foodService;

    private static final String MAIN_SERVICE = "mainService";

    /**
     * Search a certain food by providing an input string
     *
     * @param foodSearchKeyword the search keyword
     */
    @GetMapping("/search")
    public Mono<ResponseEntity<List<Food>>> getFoodByGeneralSearchInput(@RequestParam String foodSearchKeyword,
                                                                        @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        log.info("Search food by using keyword(s): {}", foodSearchKeyword);
        return Mono.fromCallable(() -> getDeferredListResponseEntity(foodSearchKeyword, pageNumber))
                .publishOn(Schedulers.boundedElastic());
    }

    private ResponseEntity<List<Food>> getDeferredListResponseEntity(@RequestParam String foodSearchKeyword,
                                                                     @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        ResponseEntity<List<Food>> responseEntity;

        try {
            List<Food> foodByName = foodService.getFoodByName(foodSearchKeyword, pageNumber);
            responseEntity = new ResponseEntity<>(foodByName, getHttpStatusBasedOnResult(foodByName));
        } catch (ImproperRequestException e) {
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        log.info("Got: {} in thread {}", responseEntity, Thread.currentThread().getName());
        return responseEntity;
    }

    /**
     * Fetches the nutrients of a food, bu using its unique database index.
     *
     * @param foodId Unique ID of the food.
     */
    @GetMapping("/reports/{foodId}")
    //@CircuitBreaker(name = MAIN_SERVICE)
    public Mono<ResponseEntity<List<FoodNutrient>>> getNutrition(@PathVariable String foodId) throws InterruptedException {
        log.info("Search nutrient by using ndbNo: {}", foodId);

        return Mono.fromCallable(() -> getDeferredNutrition(foodId))
                .publishOn(Schedulers.boundedElastic());
    }

    private ResponseEntity<List<FoodNutrient>> getDeferredNutrition(@PathVariable String foodId) {
        ResponseEntity<List<FoodNutrient>> responseEntity;

        try {
            List<FoodNutrient> foodNutrients = foodService.getFoodNutritionValue(foodId);
            responseEntity = new ResponseEntity<>(foodNutrients, getHttpStatusBasedOnResult(foodNutrients));
        } catch (ImproperRequestException e) {
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        log.info("Got: {} in thread {}", responseEntity, Thread.currentThread().getName());
        return responseEntity;
    }

    private HttpStatus getHttpStatusBasedOnResult(List<?> list) {
        return list != null && list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    }


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/getSleuthTest")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = MAIN_SERVICE, fallbackMethod="testFallBack")
    public ResponseEntity<String> getSleuthTest() throws InterruptedException {
        log.info("I'm here in main service calling service one");
        String response = restTemplate.getForObject("http://localhost:8081/serviceOne", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);

    }


    private  ResponseEntity<String> testFallBack(Exception e){
        return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
