package com.tarantini.lcbo;

import com.tarantini.lcbo.configuration.HeaderRequestInterceptor;
import com.tarantini.lcbo.configuration.LoggingRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    RestTemplate restTemplate(
            final HeaderRequestInterceptor headerRequestInterceptor,
            final LoggingRequestInterceptor loggingRequestInterceptor
    ) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(headerRequestInterceptor, loggingRequestInterceptor));
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
