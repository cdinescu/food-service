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

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.response.parser.FoodListParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FoodJsonExtractorTest {
    private FoodListParser foodListParser = new FoodListParser();

    @Test
    public void testGetFoodResponseBodyOK() {
        String body = getTwoBananasResponseBody();

        // Act
        List<Food> foodList = foodListParser.parseData(body);

        // Assert
        Assert.assertEquals(2, foodList.size());
    }

    @Test
    public void testGetFoodResponseBodyEmpty() {
        // Act
        List<Food> foodList = foodListParser.parseData("");

        // Assert
        Assert.assertEquals(0, foodList.size());
    }

    @Test
    public void testGetFoodResponseBodyNull() {
        // Act
        List<Food> foodList = foodListParser.parseData(null);

        // Assert
        Assert.assertEquals(0, foodList.size());
    }

    @Test
    public void testGetFoodResponseBodyWithUnexpectedFormat() {
        // Arrange
        String body = "          {\n" +
                "                \"label\": \"tbsp\",\n" +
                "                \"eqv\": 6.2,\n" +
                "                \"eunit\": \"g\",\n" +
                "                \"qty\": 1.0,\n" +
                "                \"value\": \"0.174\"\n" +
                "            }";
        // Act
        List<Food> foodList = foodListParser.parseData(body);

        // Assert
        Assert.assertEquals(0, foodList.size());
    }

    private String getTwoBananasResponseBody() {
        return "{\n" +
                "    \"list\": {\n" +
                "        \"q\": \"banana\",\n" +
                "        \"sr\": \"1\",\n" +
                "        \"ds\": \"any\",\n" +
                "        \"start\": 0,\n" +
                "        \"end\": 2,\n" +
                "        \"total\": 928,\n" +
                "        \"group\": \"\",\n" +
                "        \"sort\": \"r\",\n" +
                "        \"item\": [\n" +
                "            {\n" +
                "                \"offset\": 0,\n" +
                "                \"group\": \"Fruits and Fruit Juices\",\n" +
                "                \"name\": \"Bananas, dehydrated, or banana powder\",\n" +
                "                \"ndbno\": \"09041\",\n" +
                "                \"ds\": \"SR\",\n" +
                "                \"manu\": \"none\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"offset\": 1,\n" +
                "                \"group\": \"Branded Food Products Database\",\n" +
                "                \"name\": \"DEL MONTE, PURE EARTH, 100% PINEAPPLE BANANA COCONUT JUICE, PINEAPPLE, BANANA, COCONUT, UPC: 717524921000\",\n" +
                "                \"ndbno\": \"45235233\",\n" +
                "                \"ds\": \"LI\",\n" +
                "                \"manu\": \"Del Monte Fresh Produce N.A., Inc.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}\n";
    }
}
