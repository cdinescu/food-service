package com.vitanum.foodservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "nutrient")
public class Nutrient {
    @JsonProperty
    private String number;
    @JsonProperty
    private String name;
    @JsonProperty
    private String unitName;
}
