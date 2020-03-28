package com.vitanum.foodservice.utils;

import com.vitanum.foodservice.exceptions.ImproperRequestException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for UriComponentBuilderUtils.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UriComponentBuilderUtilsTest {
    private UriComponentBuilderUtils builderUtil = new UriComponentBuilderUtils();

    @Test
    public void getUriComponentsBuilderForFoodSearch_NullToken() {
        assertThrows(ImproperRequestException.class, () ->
                builderUtil.getUriComponentsBuilderForFoodSearch(null, 0));
    }

    @Test
    public void getUriComponentsBuilderForFoodSearch_EmptyToken() {
        assertThrows(ImproperRequestException.class, () ->
                builderUtil.getUriComponentsBuilderForFoodSearch("", 0)
        );
    }

    @Test
    public void getUriComponentBuilderForFoodReport_NullToken() {
        assertThrows(ImproperRequestException.class, () ->
                builderUtil.getUriComponentBuilderForFoodReport(null)
        );
    }

    @Test
    public void getUriComponentBuilderForFoodReport_EmptyToken() {
        assertThrows(ImproperRequestException.class, () ->
                builderUtil.getUriComponentBuilderForFoodReport("")
        );
    }

}
