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

import com.fasterxml.jackson.databind.MappingIterator;
import com.vitanum.foodservice.entities.Measurement;
import com.vitanum.foodservice.entities.Nutrient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NutrientJsonExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(NutrientJsonExtractor.class);

    private NutrientJsonExtractor() {

    }

    public static List<Nutrient> getNutrients(List<Nutrient> allNutrients, String body) {
        try {
            MappingIterator<Map> mapMappingIterator = JsonUtils.getMapMappingIterator(body);

            extractNutrientsFromResponse(allNutrients, mapMappingIterator);
        } catch (IOException exception) {
            LOG.error("Failed to extract food: ", exception);
        }
        return allNutrients;
    }

    private static void extractNutrientsFromResponse(List<Nutrient> allNutrients, MappingIterator<Map> mapMappingIterator) {
        for (Iterator it = mapMappingIterator; it.hasNext(); ) {
            Map next = (Map) it.next();
            Map<String, Object> list = (Map<String, Object>) next.get("report");
            Map<String, Object> food = (Map<String, Object>) list.get("food");
            List<Map<String, Object>> nutrients = (List<Map<String, Object>>) food.get("nutrients");

            nutrients.forEach(nutrientsMap -> extractNutrient(allNutrients, nutrientsMap));

        }
    }

    private static void extractNutrient(List<Nutrient> allNutrients, Map<String, Object> nutrientsMap) {
        String nutrientId = (String) nutrientsMap.get("nutrient_id");
        String nutrientName = (String) nutrientsMap.get("name");
        String unit = (String) nutrientsMap.get("unit");
        Double amount = Double.valueOf((String) nutrientsMap.get("value"));

        List<Measurement> measures = (List<Measurement>) nutrientsMap.get("measures");
        System.out.println("MEASURES " + measures);
        Nutrient nutrient = new Nutrient.NutrientBuilder()
                .setNutrientId(nutrientId)
                .setNutrientName(nutrientName)
                .setUnit(unit)
                .setAmount(amount)
                .setMeasurements(measures)
                .build();

        allNutrients.add(nutrient);
    }
}
