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
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonRootName(value = "list")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodResponse {
    @JsonProperty
    private String q;
    @JsonProperty
    private String sr;
    @JsonProperty
    private String ds;
    @JsonProperty
    private Integer start;
    @JsonProperty
    private Integer end;
    @JsonProperty
    private Integer total;
    @JsonProperty
    private String group;
    @JsonProperty
    private String sort;
    @JsonProperty
    private List<Food> item;

}
