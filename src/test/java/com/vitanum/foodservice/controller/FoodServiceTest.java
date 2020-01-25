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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FoodServiceTest {
    @Value("${max.results.per.query}")
    private Integer maxResultsPerQuery;

    @Autowired
    private FoodService foodService;

    @Test
    public void testGetFoods() {
        // Arrange and Act
        List<Food> retrievedFoods = foodService.getFoodByName("banana");

        // Assert
        assertNotNull(retrievedFoods);
        assertEquals(maxResultsPerQuery, retrievedFoods.size());
        retrievedFoods.stream().allMatch(food -> "banana".equals(food.getName().toLowerCase()));
    }

    @Test
    public void testGetFoodsWithDummyName() {
        // Arrange and Act
        List<Food> retrievedFoods = foodService.getFoodByName("dummy food");

        // Assert
        assertNotNull(retrievedFoods);
        assertEquals(0, retrievedFoods.size());
    }

    @Test
    public void testGetFoodNutrients() {
        // Arrange
        Food theFood = new Food("banana", "09041");
        assertNotNull(theFood.getNutrientIdToValueMap());
        assertEquals(0, theFood.getNutrientIdToValueMap().size());

        // Act
        foodService.getFoodNutritionValue(theFood);

        // Assert
        Map<String, Nutrient> nutrientIdToValueMap = theFood.getNutrientIdToValueMap();
        assertNotNull(nutrientIdToValueMap);
        assertFalse(nutrientIdToValueMap.isEmpty());

        nutrientIdToValueMap.keySet().stream().forEach(s -> System.out.println(nutrientIdToValueMap.get(s)));
    }
}
