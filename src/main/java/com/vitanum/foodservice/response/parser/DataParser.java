package com.vitanum.foodservice.response.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DataParser<T> {
    protected static final Logger LOG = LoggerFactory.getLogger(FoodListParser.class);


    public List<T> parseData(String body) {
        return Strings.isBlank(body) ? Collections.emptyList() : parseDataFrom(body);
    }

    private List<T> parseDataFrom(String body) {
        List<T> allEntities = new ArrayList<>();
        try {
            JsonParser parser = getJsonParser(body);
            ObjectMapper objectMapper = getObjectMapper();

            allEntities = getUsdaEntities(parser, objectMapper);
        } catch (IOException exception) {
            LOG.error("Failed to extract object from JSON: ", exception);
        }

        return allEntities;
    }

    protected ObjectMapper getObjectMapper() {
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        // deserialize by using the root value as well
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    private JsonParser getJsonParser(String body) throws IOException {
        return new JsonFactory().createParser(body);
    }

    protected abstract List<T> getUsdaEntities(JsonParser parser, ObjectMapper objectMapper) throws IOException;
}
