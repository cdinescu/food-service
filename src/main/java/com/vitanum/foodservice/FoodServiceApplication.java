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

package com.vitanum.foodservice;

import com.vitanum.foodservice.controller.FoodService;
import com.vitanum.foodservice.controller.FoodServiceImpl;
import com.vitanum.foodservice.utils.UriComponentBuilderUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FoodService foodService() {
        return new FoodServiceImpl();
    }

    @Bean
    public UriComponentBuilderUtils uriBuilder() {
        return new UriComponentBuilderUtils();
    }
}