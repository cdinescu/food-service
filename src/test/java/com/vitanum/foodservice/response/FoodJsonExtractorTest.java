package com.vitanum.foodservice.response;

import com.vitanum.foodservice.entities.Food;
import com.vitanum.foodservice.response.parser.FoodListParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodJsonExtractorTest {
    private final FoodListParser foodListParser = new FoodListParser();

    @Test
    public void testGetFoodResponseBodyOKFoodWithoutNdbno() {
        String body = createFoodResponseBodyWithoutNdbno();

        // Act
        List<Food> foodList = foodListParser.parseData(body);

        // Assert
        assertEquals(2, foodList.size());
    }

    @Test
    public void testGetFoodResponseBodyEmpty() {
        // Act
        List<Food> foodList = foodListParser.parseData("");

        // Assert
        assertEquals(0, foodList.size());
    }

    @Test
    public void testGetFoodResponseBodyNull() {
        // Act
        List<Food> foodList = foodListParser.parseData(null);

        // Assert
        assertEquals(0, foodList.size());
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
        assertEquals(0, foodList.size());
    }

    private String createFoodResponseBodyWithoutNdbno() {
        return "{\n" +
                "\"foodSearchCriteria\": {\n" +
                "\"generalSearchInput\": \"Banana\",\n" +
                "\"pageNumber\": 1,\n" +
                "\"requireAllWords\": false\n" +
                "},\n" +
                "\"totalHits\": 3181,\n" +
                "\"currentPage\": 1,\n" +
                "\"totalPages\": 64,\n" +
                "\"foods\": [\n" +
                "{\n" +
                "\"fdcId\": 362759,\n" +
                "\"description\": \"BANANA\",\n" +
                "\"dataType\": \"Branded\",\n" +
                "\"gtinUpc\": \"609207617761\",\n" +
                "\"publishedDate\": \"2019-04-01\",\n" +
                "\"brandOwner\": \"Kid Vids Educational Entertainment\",\n" +
                "\"ingredients\": \"BANANA, SULFUR DIOXIDE (AS A PRESERVATIVE). \",\n" +
                "\"allHighlightFields\": \"<b>Ingredients</b>: <em>BANANA</em>, SULFUR DIOXIDE (AS A PRESERVATIVE). \",\n" +
                "\"score\": 813.1304\n" +
                "},\n" +
                "{\n" +
                "\"fdcId\": 363938,\n" +
                "\"description\": \"BANANA\",\n" +
                "\"dataType\": \"Branded\",\n" +
                "\"gtinUpc\": \"016459200441\",\n" +
                "\"publishedDate\": \"2019-04-01\",\n" +
                "\"brandOwner\": \"Wonder Natural Foods Corp\",\n" +
                "\"ingredients\": \"PEANUTS (AS DEFATTED PEANUT FLOUR, PEANUT BUTTER AND NATURAL PEANUT OILS), PURE WATER, TAPIOCA SYRUP, RICE SYRUP, VEGETABLE GLYCERINE, CANE SUGAR, BANANA, NATURAL COLORS AND FLAVORS, SALT, CALCIUM CARBONATE, LECITHIN, TOCOPHEROL (VITAMIN E), SODIUM ASCORBATE (VITAMIN C).\",\n" +
                "\"allHighlightFields\": \"<b>Ingredients</b>:  SYRUP, RICE SYRUP, VEGETABLE GLYCERINE, CANE SUGAR, <em>BANANA</em>, NATURAL COLORS AND FLAVORS, SALT, CALCIUM\",\n" +
                "\"score\": 813.1304\n" +
                "}\n" +
                "]\n" +
                "}";
    }
}
