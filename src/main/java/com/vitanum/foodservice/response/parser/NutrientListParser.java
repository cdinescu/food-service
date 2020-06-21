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
