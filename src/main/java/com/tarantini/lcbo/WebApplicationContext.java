package com.tarantini.lcbo;

import com.tarantini.lcbo.configuration.HeaderRequestInterceptor;
import com.tarantini.lcbo.configuration.LoggingRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class WebApplicationContext {
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
}
