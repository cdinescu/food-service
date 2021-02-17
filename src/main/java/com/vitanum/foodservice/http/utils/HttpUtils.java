package com.vitanum.foodservice.http.utils;

import org.springframework.http.HttpHeaders;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class HttpUtils {

    private HttpUtils() {
    }

    public static HttpHeaders createHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        return headers;
    }
}
