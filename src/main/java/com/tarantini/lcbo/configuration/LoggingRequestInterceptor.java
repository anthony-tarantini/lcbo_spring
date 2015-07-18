package com.tarantini.lcbo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    }

    @Override
    public ClientHttpResponse intercept(
            final HttpRequest request,
            final byte[] body,
            final ClientHttpRequestExecution execution
    ) throws IOException {
        final ClientHttpResponse response = execution.execute(request, body);
        logger.info("-----------------------------------------------------");
        logger.info("REQUEST:---------------------------------------------");
        logger.info(request.getMethod().toString());
        logger.info(request.getURI().toString());
        logger.info(request.getHeaders().toString());
        logger.info("-----------------------------------------------------");
        logger.info("RESPONSE:--------------------------------------------");
        logger.info(response.getHeaders().toString());
        logger.info(response.getStatusCode().toString());
        logger.info(response.getStatusText());
        logger.info("-----------------------------------------------------");
        return response;
    }
}
