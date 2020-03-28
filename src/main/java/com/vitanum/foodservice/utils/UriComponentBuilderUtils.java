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

import com.vitanum.foodservice.exceptions.ImproperRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UriComponentBuilderUtils {
    @Value("${usda.search.url}")
    private String foodSearchServiceURL;

    @Value("${usda.reports.url}")
    private String foodReportsServiceURL;

    @Value("${api.key}")
    private String foodServiceApiKey;

    @Value("${response.format}")
    private String responseFormat;

    @Value("${usda.requireAllWords}")
    private String requireAllWords;

    public UriComponentsBuilder getUriComponentsBuilderForFoodSearch(String foodSearchKeyword) throws ImproperRequestException {
        sanitizeRequestParameter(foodSearchKeyword);

        try {
            String encodedSearchQuery = URLEncoder.encode(foodSearchKeyword, StandardCharsets.UTF_8.toString());
            foodSearchKeyword = encodedSearchQuery;
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to encode {}. The search will use the unchanged token");
        }

        return UriComponentsBuilder.fromHttpUrl(foodSearchServiceURL)
                .queryParam("format", responseFormat)
                .queryParam("generalSearchInput", foodSearchKeyword)
                .queryParam("requireAllWords", requireAllWords)
                .queryParam("api_key", foodServiceApiKey);
    }

    public UriComponentsBuilder getUriComponentBuilderForFoodReport(String foodDbNo) throws ImproperRequestException {
        sanitizeRequestParameter(foodDbNo);

        return UriComponentsBuilder.fromHttpUrl(foodReportsServiceURL)
                .queryParam("format", responseFormat)
                .queryParam("ndbno", foodDbNo)
                .queryParam("api_key", foodServiceApiKey);
    }

    private void sanitizeRequestParameter(String requestParameter) throws ImproperRequestException {
        if (requestParameter == null || requestParameter.isEmpty()) {
            throw new ImproperRequestException("Failed to send request: improper parameter");
        }
    }
}
