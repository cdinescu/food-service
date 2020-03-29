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
import com.vitanum.foodservice.entities.Nutrient;
import com.vitanum.foodservice.exceptions.ImproperRequestException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.vitanum.foodservice.constants.Constants.FOOD_ID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(FoodServiceTest.class);

    @Autowired
    private FoodService foodService;

    @Test
    public void testGetFoods() throws ImproperRequestException {
        // Arrange and Act
        List<Food> retrievedFoods = foodService.getFoodByName("banana", 0);

        // Assert
        assertNotNull(retrievedFoods);
        boolean allMatch = retrievedFoods.stream().allMatch(food -> food.getDescription().toLowerCase().contains("banana"));
        assertTrue(allMatch);
    }

    @Test
    public void testGetFoodsWithDummyName() throws ImproperRequestException {
        // Arrange and Act
        List<Food> retrievedFoods = foodService.getFoodByName("dummy food", 0);

        // Assert
        assertNotNull(retrievedFoods);
        assertEquals(0, retrievedFoods.size());
    }

    @Test
    public void testGetFoodNutrients() throws ImproperRequestException {
        // Act
        List<FoodNutrient> foodNutrients = foodService.getFoodNutritionValue(FOOD_ID);

        // Assert
        assertNotNull(foodNutrients);
        assertFalse(foodNutrients.isEmpty());

        foodNutrients.forEach(nutrient -> LOG.info("Nutrient {}", nutrient));
    }

}
