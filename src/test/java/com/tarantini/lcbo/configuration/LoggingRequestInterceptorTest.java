package com.tarantini.lcbo.configuration;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LoggingRequestInterceptorTest {

    @Test
    public void intercept_logsRequest() throws Exception {
        final Logger mockLogger = mock(Logger.class);
        LoggingRequestInterceptor.setLogger(mockLogger);
        final LoggingRequestInterceptor interceptor = new LoggingRequestInterceptor();
        final HttpRequest mockRequest = mock(HttpRequest.class);
        final ClientHttpRequestExecution mockExecution = mock(ClientHttpRequestExecution.class);
        final ClientHttpResponse mockResponse = mock(ClientHttpResponse.class);
        final byte[] body = new byte[0];
        doReturn(mockResponse).when(mockExecution).execute(any(HttpRequest.class), any(byte[].class));
        doReturn(HttpMethod.GET).when(mockRequest).getMethod();
        doReturn(URI.create("http://localhost:8080")).when(mockRequest).getURI();
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Request", "Header");
        doReturn(requestHeaders).when(mockRequest).getHeaders();
        doReturn(HttpStatus.ACCEPTED).when(mockResponse).getStatusCode();
        doReturn("Accepted").when(mockResponse).getStatusText();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Response", "Header");
        doReturn(responseHeaders).when(mockResponse).getHeaders();

        final ClientHttpResponse response = interceptor.intercept(mockRequest, body, mockExecution);

        verify(mockLogger, times(3)).info("-----------------------------------------------------");
        verify(mockLogger).info("REQUEST:---------------------------------------------");
        verify(mockLogger).info("GET");
        verify(mockLogger).info("http://localhost:8080");
        verify(mockLogger).info("{Request=[Header]}");
        verify(mockLogger).info("RESPONSE:--------------------------------------------");
        verify(mockLogger).info("{Response=[Header]}");
        verify(mockLogger).info("202");
        verify(mockLogger).info("Accepted");
        verify(mockExecution).execute(mockRequest, body);
        assertThat(response).isSameAs(mockResponse);
    }
}