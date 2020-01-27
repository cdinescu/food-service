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

import java.util.List;

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

    public String getQ() {
        return q;
    }

    public String getSr() {
        return sr;
    }

    public String getDs() {
        return ds;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getTotal() {
        return total;
    }

    public String getGroup() {
        return group;
    }

    public String getSort() {
        return sort;
    }

    public List<Food> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "FoodResponse{" +
                "q='" + q + '\'' +
                ", sr='" + sr + '\'' +
                ", ds='" + ds + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", total=" + total +
                ", group='" + group + '\'' +
                ", sort='" + sort + '\'' +
                ", item=" + item +
                '}';
    }
}
