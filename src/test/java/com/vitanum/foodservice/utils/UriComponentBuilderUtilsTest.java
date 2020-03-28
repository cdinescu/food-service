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
    public void getUriComponentsBuilderForFoodSearch_NullToken() throws ImproperRequestException {
        assertThrows(ImproperRequestException.class, () -> {
            builderUtil.getUriComponentsBuilderForFoodSearch(null);
        });
    }

    @Test
    public void getUriComponentsBuilderForFoodSearch_EmptyToken() throws ImproperRequestException {
        assertThrows(ImproperRequestException.class, () -> {
            builderUtil.getUriComponentsBuilderForFoodSearch("");
        });
    }

    @Test
    public void getUriComponentBuilderForFoodReport_NullToken() throws ImproperRequestException {
        assertThrows(ImproperRequestException.class, () -> {
            builderUtil.getUriComponentBuilderForFoodReport(null);
        });
    }

    @Test
    public void getUriComponentBuilderForFoodReport_EmptyToken() throws ImproperRequestException {
        assertThrows(ImproperRequestException.class, () -> {
            builderUtil.getUriComponentBuilderForFoodReport("");
        });
    }

}
