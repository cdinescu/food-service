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
import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.exceptions.ImproperRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foods")
@Slf4j
public class FoodController {
    @Autowired
    private FoodService foodService;

    /**
     * Search a certain food by providing an input string
     *
     * @param foodSearchKeyword the search keyword
     */
    @GetMapping("/search")
    public Mono<ResponseEntity<List<Food>>> getFoodByGeneralSearchInput(@RequestParam String foodSearchKeyword,
                                                                        @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        log.info("Search food by using keyword(s): {}", foodSearchKeyword);
        return Mono.fromCallable(() -> {
            ResponseEntity<List<Food>> responseEntity;

            try {
                List<Food> foodByName = foodService.getFoodByName(foodSearchKeyword, pageNumber);
                responseEntity = new ResponseEntity<>(foodByName, getHttpStatusBasedOnResult(foodByName));
            } catch (ImproperRequestException e) {
                responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            return responseEntity;
        });
    }

    /**
     * Fetches the nutrients of a food, bu using its unique database index.
     *
     * @param foodId Unique ID of the food.
     */
    @GetMapping("/reports/{foodId}")
    public Mono<ResponseEntity<List<FoodNutrient>>> getNutrition(@PathVariable String foodId) {
        log.info("Search nutrient by using ndbNo: {}", foodId);

        return Mono.fromCallable(() -> {
            ResponseEntity<List<FoodNutrient>> responseEntity;

            try {
                List<FoodNutrient> foodNutrients = foodService.getFoodNutritionValue(foodId);
                responseEntity = new ResponseEntity<>(foodNutrients, getHttpStatusBasedOnResult(foodNutrients));
            } catch (ImproperRequestException e) {
                responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            return responseEntity;
        });
    }

    private HttpStatus getHttpStatusBasedOnResult(List<?> list) {
        return list != null && list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    }
}
