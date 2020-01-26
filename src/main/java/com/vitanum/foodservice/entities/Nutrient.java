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

import java.util.List;

public class Nutrient {
    private String nutrientId;
    private String nutrientName;
    private Double amount;
    private String unit;
    private List<Measurement> measurements;

    public Nutrient(NutrientBuilder theNutrientBuilder) {
        this.nutrientId = theNutrientBuilder.nutrientId;
        this.nutrientName = theNutrientBuilder.nutrientName;
        this.amount = theNutrientBuilder.amount;
        this.unit = theNutrientBuilder.unit;
        this.measurements = theNutrientBuilder.measurements;
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

    public List<Measurement> getMeasures() {
        return measurements;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "nutrientId='" + nutrientId + '\'' +
                ", nutrientName='" + nutrientName + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", measurements=" + measurements +
                '}';
    }

    public static class NutrientBuilder {
        String nutrientId;
        String nutrientName;
        Double amount;
        String unit;
        List<Measurement> measurements;

        public NutrientBuilder setNutrientId(String nutrientId) {
            this.nutrientId = nutrientId;
            return this;
        }

        public NutrientBuilder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public NutrientBuilder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public NutrientBuilder setNutrientName(String nutrientName) {
            this.nutrientName = nutrientName;
            return this;
        }

        public NutrientBuilder setMeasurements(List<Measurement> measurements) {
            this.measurements = measurements;
            return this;
        }

        public Nutrient build() {
            return new Nutrient(this);
        }
    }

}
