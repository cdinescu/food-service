package com.vitanum.foodservice.response;

import com.vitanum.foodservice.entities.FoodNutrient;
import com.vitanum.foodservice.response.parser.NutrientListParser;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.Assert.assertNotNull;

/**
 * Test class for NutrientJsonExtractor.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class NutrientJsonExtractorTest {
    private final NutrientListParser nutrientListParser = new NutrientListParser();

    @Test
    public void testGetNutrientsResponseBodyOK() {
        // Arrange
        String body = getReportResponseBody();

        // Act
        List<FoodNutrient> nutrientList = nutrientListParser.parseData(body);

        // Assert
        nutrientList.forEach(foodNutrient -> {
            assertNotNull(foodNutrient.getNutrient());
            assertNotNull(foodNutrient.getAmount());
        });
        Assert.assertEquals(65, nutrientList.size());
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
        try {
            InputStream resource = new ClassPathResource(
                    "data/reportResponse.json").getInputStream();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource))) {
                reportBody = reader.lines()
                        .collect(Collectors.joining("\n"));
            } catch (IOException e) {
                log.error("Failed to read file: ", e);
            }
        } catch (IOException e) {
            log.error("Failed to read file: ", e);
        }

        return reportBody;
    }
}
