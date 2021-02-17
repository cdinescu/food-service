package com.vitanum.foodservice.response.parser;

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.FoodNutrient;
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

    public static List<FoodNutrient> extractNutrients(ResponseEntity<String> response) {
        return isResponseOk(response) ? new NutrientListParser().parseData(response.getBody()) : new ArrayList<>();
    }

    private static boolean isResponseOk(ResponseEntity<String> response) {
        return response != null && response.getStatusCode() == HttpStatus.OK && response.hasBody();
    }
}
