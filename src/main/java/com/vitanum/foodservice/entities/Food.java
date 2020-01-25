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

package com.vitanum.foodservice.entities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Food {
    private String name;

    private String ndbNo;

    private Map<String, Nutrient> nutrientIdToValueMap = new LinkedHashMap<>();

    public Food(String name, String ndbNo) {
        this.name = name;
        this.ndbNo = ndbNo;
    }

    public String getName() {
        return name;
    }

    public String getNdbNo() {
        return ndbNo;
    }

    public Map<String, Nutrient> getNutrientIdToValueMap() {
        return nutrientIdToValueMap;
    }

    public void addNutrientValues(List<Nutrient> nutrientValues) {
        if (nutrientValues != null) {
            for (Nutrient nutrientValue : nutrientValues) {
                this.addNutrientValue(nutrientValue);
            }
        }
    }

    public void addNutrientValue(Nutrient nutrientValue) {
        this.nutrientIdToValueMap.computeIfAbsent(nutrientValue.getNutrientId(), (id) -> nutrientValue);
    }


    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", ndbNo='" + ndbNo + '\'' +
                ", nutrientIdToValueMap=" + nutrientIdToValueMap +
                '}';
    }
}
