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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.entities.Nutrient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FoodJsonParser {
    private static final Logger LOG = LoggerFactory.getLogger(FoodJsonParser.class);

    private FoodJsonParser() {

    }

    public static List<Food> extractFoodFromJson(String body) {
        List<Food> result = new ArrayList<>();

        if (body == null || body.isEmpty()) {
            // TODO create an exception which will later translate to error 404.
            return result;
        }

        try {
            MappingIterator<Map> mapMappingIterator = new ObjectMapper().readValues(
                    new JsonFactory().createParser(body), Map.class);
            for (Iterator it = mapMappingIterator; it.hasNext(); ) {
                Map next = (Map) it.next();
                Map<String, Object> list = (Map<String, Object>) next.get("list");

                fetchFoodFromItems(result, list);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private static void fetchFoodFromItems(List<Food> result, Map<String, Object> list) {
        if (list == null || list.isEmpty()) {
            // TODO create exception
            return;
        }

        List<Map<String, String>> item = (List<Map<String, String>>) list.get("item");

        for (Map<String, String> map : item) {
            String foodName = map.get("name");
            String ndbno = map.get("ndbno");
            // System.out.println(foodName + "--- " + ndbno);
            result.add(new Food(foodName, ndbno));
        }
    }

    public static List<Nutrient> extractNutrientsFromJson(String body) {
        List<Nutrient> allNutrients = new ArrayList<>();

        MappingIterator<Map> mapMappingIterator = null;
        try {
            mapMappingIterator = new ObjectMapper().readValues(
                    new JsonFactory().createParser(body), Map.class);

            for (Iterator it = mapMappingIterator; it.hasNext(); ) {
                Map next = (Map) it.next();
                Map<String, Object> list = (Map<String, Object>) next.get("report");

                Map<String, Object> food = (Map<String, Object>) list.get("food");

                List<Map<String, Object>> nutrients = (List<Map<String, Object>>) food.get("nutrients");

                //System.out.println("Nutri:---- " + nutrients);
                nutrients.stream().forEach(nutrientsMap -> {
                    String nutrientId = (String) nutrientsMap.get("nutrient_id");
                    String nutrientName = (String) nutrientsMap.get("name");
                    String unit = (String) nutrientsMap.get("unit");
                    Double amount = Double.valueOf((String) nutrientsMap.get("value"));
                    // List<Map<String, String> measures = (Map<String, String>) nutrientsMap.get("measures");

                    Nutrient nutrient = new Nutrient.NutrientBuilder().setNutrientId(nutrientId).setNutrientName(nutrientName).setUnit(unit).setAmount(amount).build();
                    //nutrient.setMeasures(measures);

                    allNutrients.add(nutrient);
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allNutrients;
    }
}
