package com.vitanum.foodservice.response.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.entities.FoodReportResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NutrientListParser extends DataParser<FoodNutrient> {

    @Override
    protected List<FoodNutrient> getUsdaEntities(JsonParser parser, ObjectMapper objectMapper) throws IOException {
        FoodReportResponse report = objectMapper.readValue(parser, FoodReportResponse.class);
        LOG.info("Extracted food report response: {}", report);
        List<FoodNutrient> nutrients = report.getFoodNutrients();

        return nutrients != null ? nutrients : new ArrayList<>();
    }
}
