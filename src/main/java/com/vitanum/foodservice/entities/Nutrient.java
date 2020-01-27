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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrient {
    @JsonProperty
    private String nutrient_id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String derivation;
    @JsonProperty
    private String group;
    @JsonProperty
    private String unit;
    @JsonProperty
    private String value;
    @JsonProperty
    private List<Measurement> measures;

    public String getNutrient_id() {
        return nutrient_id;
    }

    public String getName() {
        return name;
    }

    public String getDerivation() {
        return derivation;
    }

    public String getGroup() {
        return group;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public List<Measurement> getMeasures() {
        return measures;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "nutrient_id='" + nutrient_id + '\'' +
                ", name='" + name + '\'' +
                ", derivation='" + derivation + '\'' +
                ", group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                ", measures=" + measures +
                '}';
    }
}
