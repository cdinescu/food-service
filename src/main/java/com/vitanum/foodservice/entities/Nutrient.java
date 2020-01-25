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

import java.util.HashMap;
import java.util.Map;

public class Nutrient {
    private String nutrientId;
    private String nutrientName;
    private Double amount;
    private String unit;
    private Map<String, String> measures;

    //TODO add Builder
    public Nutrient(String nutrientId, String nutrientName, Double amount, String unit) {
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.amount = amount;
        this.unit = unit;

        this.measures = new HashMap<>();
    }

    public String getNutrientId() {
        return nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public Map<String, String> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, String> measures) {
        this.measures = measures;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "nutrientId='" + nutrientId + '\'' +
                ", nutrientName='" + nutrientName + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", measures=" + measures +
                '}';
    }
}
