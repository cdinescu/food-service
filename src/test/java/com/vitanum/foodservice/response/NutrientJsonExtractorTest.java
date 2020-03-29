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

package com.vitanum.foodservice.response;

import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.response.parser.NutrientListParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class for NutrientJsonExtractor.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NutrientJsonExtractorTest {
    private final NutrientListParser nutrientListParser = new NutrientListParser();

    @Test
    public void testGetNutrientsResponseBodyOK() {
        // Arrange
        String body = getReportResponseBody();

        // Act
        List<FoodNutrient> nutrientList = nutrientListParser.parseData(body);

        // Assert
        Assert.assertEquals(113, nutrientList.size());
    }

    @Test
    public void testGetNutrientsResponseBodyEmpty() {
        // Act
        List<FoodNutrient> nutrientList = nutrientListParser.parseData("");

        // Assert
        Assert.assertEquals(0, nutrientList.size());
    }

    @Test
    public void testGetNutrientsResponseBodyNull() {
        // Act
        List<FoodNutrient> nutrientList = nutrientListParser.parseData(null);

        // Assert
        Assert.assertEquals(0, nutrientList.size());
    }

    @Test
    public void testGetNutrientsResponseBodyWithUnexpectedResult() {
        // Arrange
        String body = "          {\n" +
                "                \"label\": \"tbsp\",\n" +
                "                \"eqv\": 6.2,\n" +
                "                \"eunit\": \"g\",\n" +
                "                \"qty\": 1.0,\n" +
                "                \"value\": \"0.174\"\n" +
                "            }";

        // Act
        List<FoodNutrient> nutrientList = nutrientListParser.parseData(body);

        // Assert
        Assert.assertEquals(0, nutrientList.size());
    }

    private String getReportResponseBody() {
        String reportBody = "";
        InputStream resource = null;
        try {
            resource = new ClassPathResource(
                    "data/reportResponse.json").getInputStream();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource))) {
                reportBody = reader.lines()
                        .collect(Collectors.joining("\n"));
            } catch (IOException e) {
            }
        } catch (IOException e) {
        }

        return reportBody;
    }
}
