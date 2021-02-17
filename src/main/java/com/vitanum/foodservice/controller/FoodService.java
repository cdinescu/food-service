package com.vitanum.foodservice.controller;

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.exceptions.ImproperRequestException;

import java.util.List;

public interface FoodService {
    List<Food> getFoodByName(String foodSearchKeyword, Integer pageNumber) throws ImproperRequestException;
    
    List<FoodNutrient> getFoodNutritionValue(String foodId) throws ImproperRequestException;
}
