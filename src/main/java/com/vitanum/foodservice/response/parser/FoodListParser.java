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
        LOG.info("Extracted food response: {}", foodResponse);
        List<Food> foods = foodResponse.getFoods();

        return foods != null ? foods : new ArrayList<>();
    }
}
