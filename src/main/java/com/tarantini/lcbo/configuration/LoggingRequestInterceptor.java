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
    private static Logger sLogger;

    static {
        sLogger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    }

    public static void setLogger(final Logger logger) {
        sLogger = logger;
    }

    @Override
    public ClientHttpResponse intercept(
            final HttpRequest request,
            final byte[] body,
            final ClientHttpRequestExecution execution
    ) throws IOException {
        final ClientHttpResponse response = execution.execute(request, body);
        sLogger.info("-----------------------------------------------------");
        sLogger.info("REQUEST:---------------------------------------------");
        sLogger.info(request.getMethod().toString());
        sLogger.info(request.getURI().toString());
        sLogger.info(request.getHeaders().toString());
        sLogger.info("-----------------------------------------------------");
        sLogger.info("RESPONSE:--------------------------------------------");
        sLogger.info(response.getHeaders().toString());
        sLogger.info(response.getStatusCode().toString());
        sLogger.info(response.getStatusText());
        sLogger.info("-----------------------------------------------------");
        return response;
    }
}
