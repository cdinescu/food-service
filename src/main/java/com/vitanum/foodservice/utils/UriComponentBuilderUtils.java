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

package com.vitanum.foodservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentBuilderUtils {
    @Value("${usda.search.url}")
    private String foodSearchServiceURL;

    @Value("${api.key}")
    private String foodServiceApiKey;

    @Value("${max.results.per.query}")
    private Integer maxResultsPerQuery;

    @Value("${response.format}")
    private String responseFormat;

    public UriComponentsBuilder getUriComponentsBuilderForFoodSearch(String foodSearchKeyword) {
        return UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL + "/search")
                .queryParam("format", responseFormat)
                .queryParam("q", foodSearchKeyword)
                .queryParam("max", maxResultsPerQuery)
                .queryParam("offset", 0)
                .queryParam("api_key", foodServiceApiKey);
    }

    public UriComponentsBuilder getUriComponentBuilderForFoodReport(String foodDbNo) {
        return UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL + "/reports")
                .queryParam("format", responseFormat)
                .queryParam("ndbno", foodDbNo)
                .queryParam("api_key", foodServiceApiKey);
    }
}
