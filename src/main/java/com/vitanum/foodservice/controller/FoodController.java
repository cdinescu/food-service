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
import com.vitanum.foodservice.exceptions.ImproperRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    /**
     * Search a certain food by providing an input string
     *
     * @param foodSearchKeyword the search keyword
     */
    @GetMapping("/search")
    public ResponseEntity<List<Food>> getFoodByGeneralSearchInput(@RequestParam String foodSearchKeyword) {
        ResponseEntity<List<Food>> responseEntity;

        try {
            List<Food> foodByName = foodService.getFoodByName(foodSearchKeyword);
            responseEntity = new ResponseEntity<>(foodByName, getHttpStatusBasedOnResult(foodByName));
        } catch (ImproperRequestException e) {
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * fetches the nutrients of a food, bu using its unique database index.
     *
     * @param ndbNo the food unique identifier (as found in the USDA)
     */
    @GetMapping("/reports")
    public ResponseEntity<List<Nutrient>> getNutrition(@RequestParam String ndbNo) {
        ResponseEntity<List<Nutrient>> responseEntity;
        try {
            List<Nutrient> foodNutrients = foodService.getFoodNutritionValue(ndbNo);
            responseEntity = new ResponseEntity<>(foodNutrients, getHttpStatusBasedOnResult(foodNutrients));
        } catch (ImproperRequestException e) {
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    private HttpStatus getHttpStatusBasedOnResult(List<?> list) {
        return list != null && list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    }
}
