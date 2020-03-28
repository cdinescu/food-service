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

import com.vitanum.foodservice.entities.Nutrient;
import com.vitanum.foodservice.response.parser.NutrientListParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Test class for NutrientJsonExtractor.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NutrientJsonExtractorTest {
    private final NutrientListParser nutrientListParser = new NutrientListParser();

    @Test
    public void testGetNutrientsResponseBodyOK() {
        // Arrange
        String body = getTwoBananaNutrientsResponseBody();

        // Act
        List<Nutrient> nutrientList = nutrientListParser.parseData(body);

        // Assert
        Assert.assertEquals(32, nutrientList.size());
    }

    @Test
    public void testGetNutrientsResponseBodyEmpty() {
        // Act
        List<Nutrient> nutrientList = nutrientListParser.parseData("");

        // Assert
        Assert.assertEquals(0, nutrientList.size());
    }

    @Test
    public void testGetNutrientsResponseBodyNull() {
        // Act
        List<Nutrient> nutrientList = nutrientListParser.parseData(null);

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
        List<Nutrient> nutrientList = nutrientListParser.parseData(body);

        // Assert
        Assert.assertEquals(0, nutrientList.size());
    }

    private String getTwoBananaNutrientsResponseBody() {
        return "{\n" +
                "    \"report\": {\n" +
                "        \"sr\": \"1\",\n" +
                "        \"type\": \"Basic\",\n" +
                "        \"food\": {\n" +
                "            \"ndbno\": \"09041\",\n" +
                "            \"name\": \"Bananas, dehydrated, or banana powder\",\n" +
                "            \"ds\": \"Standard Reference\",\n" +
                "            \"manu\": \"\",\n" +
                "            \"ru\": \"g\",\n" +
                "            \"nutrients\": [\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"255\",\n" +
                "                    \"name\": \"Water\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"3.00\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"3.00\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.19\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"208\",\n" +
                "                    \"name\": \"Energy\",\n" +
                "                    \"derivation\": \"NC\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"kcal\",\n" +
                "                    \"value\": \"346\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"346\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"21\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"203\",\n" +
                "                    \"name\": \"Protein\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"3.89\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"3.89\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.24\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"204\",\n" +
                "                    \"name\": \"Total lipid (fat)\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"1.81\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1.81\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.11\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"205\",\n" +
                "                    \"name\": \"Carbohydrate, by difference\",\n" +
                "                    \"derivation\": \"NC\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"88.28\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"88.28\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"5.47\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"291\",\n" +
                "                    \"name\": \"Fiber, total dietary\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"9.9\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"9.9\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.6\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"269\",\n" +
                "                    \"name\": \"Sugars, total\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Proximates\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"47.30\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"47.30\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"2.93\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"301\",\n" +
                "                    \"name\": \"Calcium, Ca\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"22\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"22\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"303\",\n" +
                "                    \"name\": \"Iron, Fe\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"1.15\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1.15\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.07\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"304\",\n" +
                "                    \"name\": \"Magnesium, Mg\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"108\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"108\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"7\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"305\",\n" +
                "                    \"name\": \"Phosphorus, P\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"74\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"74\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"5\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"306\",\n" +
                "                    \"name\": \"Potassium, K\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"1491\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1491\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"92\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"307\",\n" +
                "                    \"name\": \"Sodium, Na\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"3\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"3\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"309\",\n" +
                "                    \"name\": \"Zinc, Zn\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Minerals\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0.61\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.61\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.04\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"401\",\n" +
                "                    \"name\": \"Vitamin C, total ascorbic acid\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"7.0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"7.0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.4\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"404\",\n" +
                "                    \"name\": \"Thiamin\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0.180\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.180\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.011\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"405\",\n" +
                "                    \"name\": \"Riboflavin\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0.240\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.240\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.015\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"406\",\n" +
                "                    \"name\": \"Niacin\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"2.800\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"2.800\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.174\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"415\",\n" +
                "                    \"name\": \"Vitamin B-6\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0.440\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.440\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.027\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"435\",\n" +
                "                    \"name\": \"Folate, DFE\",\n" +
                "                    \"derivation\": \"NC\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"\\u00b5g\",\n" +
                "                    \"value\": \"14\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"14\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"418\",\n" +
                "                    \"name\": \"Vitamin B-12\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"\\u00b5g\",\n" +
                "                    \"value\": \"0.00\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.00\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.00\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"320\",\n" +
                "                    \"name\": \"Vitamin A, RAE\",\n" +
                "                    \"derivation\": \"NC\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"\\u00b5g\",\n" +
                "                    \"value\": \"12\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"12\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"1\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"318\",\n" +
                "                    \"name\": \"Vitamin A, IU\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"IU\",\n" +
                "                    \"value\": \"248\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"248\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"15\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"323\",\n" +
                "                    \"name\": \"Vitamin E (alpha-tocopherol)\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0.39\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.39\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.02\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"328\",\n" +
                "                    \"name\": \"Vitamin D (D2 + D3)\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"\\u00b5g\",\n" +
                "                    \"value\": \"0.0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"324\",\n" +
                "                    \"name\": \"Vitamin D\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"IU\",\n" +
                "                    \"value\": \"0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"430\",\n" +
                "                    \"name\": \"Vitamin K (phylloquinone)\",\n" +
                "                    \"derivation\": \"BFSN\",\n" +
                "                    \"group\": \"Vitamins\",\n" +
                "                    \"unit\": \"\\u00b5g\",\n" +
                "                    \"value\": \"2.0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"2.0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.1\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"606\",\n" +
                "                    \"name\": \"Fatty acids, total saturated\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Lipids\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.698\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.698\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.043\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"645\",\n" +
                "                    \"name\": \"Fatty acids, total monounsaturated\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Lipids\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.153\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.153\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.009\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"646\",\n" +
                "                    \"name\": \"Fatty acids, total polyunsaturated\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Lipids\",\n" +
                "                    \"unit\": \"g\",\n" +
                "                    \"value\": \"0.337\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.337\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0.021\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"601\",\n" +
                "                    \"name\": \"Cholesterol\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Lipids\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"nutrient_id\": \"262\",\n" +
                "                    \"name\": \"Caffeine\",\n" +
                "                    \"derivation\": \"NONE\",\n" +
                "                    \"group\": \"Other\",\n" +
                "                    \"unit\": \"mg\",\n" +
                "                    \"value\": \"0\",\n" +
                "                    \"measures\": [\n" +
                "                        {\n" +
                "                            \"label\": \"cup\",\n" +
                "                            \"eqv\": 100.0,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\": \"tbsp\",\n" +
                "                            \"eqv\": 6.2,\n" +
                "                            \"eunit\": \"g\",\n" +
                "                            \"qty\": 1.0,\n" +
                "                            \"value\": \"0\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"footnotes\": [\n" +
                "            \n" +
                "        ]\n" +
                "    }\n" +
                "}\n";
    }
}
