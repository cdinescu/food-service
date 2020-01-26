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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Food> foodByName = foodService.getFoodByName(foodSearchKeyword);
        HttpStatus status = foodByName.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(foodByName, status);
    }

    /**
     * Set the nutrients values to a food that is being provided in the HTTP request body
     *
     * @param theFood the food whose nutrients are retrieved
     */
    @GetMapping("/reports")
    public ResponseEntity<Food> getNutrition(@RequestBody Food theFood) {
        return new ResponseEntity<>(foodService.getFoodNutritionValue(theFood), HttpStatus.OK);
    }
}
