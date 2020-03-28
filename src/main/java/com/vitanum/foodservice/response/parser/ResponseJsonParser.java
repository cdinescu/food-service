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

public class ResponseJsonParser {

    private ResponseJsonParser() {

    }

    public static List<Food> extractFood(ResponseEntity<String> response) {
        return isResponseOk(response) ? new FoodListParser().parseData(response.getBody()) : new ArrayList<>();
    }

    public static List<Nutrient> extractNutrients(ResponseEntity<String> response) {
        return isResponseOk(response) ? new NutrientListParser().parseData(response.getBody()) : new ArrayList<>();
    }

    private static boolean isResponseOk(ResponseEntity<String> response) {
        return response != null && response.getStatusCode() == HttpStatus.OK && response.hasBody();
    }
}
