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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.FoodResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodListParser extends DataParser<Food> {


    @Override
    protected List<Food> getUsdaEntities(JsonParser parser, ObjectMapper objectMapper) throws IOException {
        FoodResponse foodResponse = objectMapper.readValue(parser, FoodResponse.class);

        return removeFoodsWithNullUniqueNumber(foodResponse);
    }

    private List<Food> removeFoodsWithNullUniqueNumber(FoodResponse foodResponse) {
        List<Food> result = new ArrayList<>();

        List<Food> foods = foodResponse.getFoods();
        if (foods != null) {
            foods.removeIf(food -> food.getNdbNumber() == null);
            result = foods;
        }

        return result;
    }
}
