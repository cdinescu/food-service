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
import com.vitanum.foodservice.food.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FoodJsonParser {

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
}
