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

package com.vitanum.foodservice.response.parser;

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.Nutrient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class FoodJsonParser {

    private FoodJsonParser() {

    }

    public static List<Food> extractFoodFromJson(ResponseEntity<String> response) {
        List<Food> allFoods = new ArrayList<>();

        if (isResponseNok(response)) {
            return allFoods;
        }

        return FoodJsonExtractor.getFoods(allFoods, response.getBody());
    }


    public static List<Nutrient> extractNutrientsFromJson(ResponseEntity<String> response) {
        List<Nutrient> allNutrients = new ArrayList<>();

        if (isResponseNok(response)) {
            return allNutrients;
        }

        return NutrientJsonExtractor.getNutrients(allNutrients, response.getBody());
    }

    private static boolean isResponseNok(ResponseEntity<String> response) {
        return response.getStatusCode() != HttpStatus.OK || !response.hasBody();
    }


}
