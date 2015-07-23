package com.tarantini.lcbo.configuration;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HeaderRequestInterceptorTest {

    @Test
    public void intercept_addsHeader() throws Exception {
        final HeaderRequestInterceptor interceptor = new HeaderRequestInterceptor();
        final HttpRequest mockRequest = mock(HttpRequest.class);
        final HttpHeaders mockHeaders = mock(HttpHeaders.class);
        final ClientHttpRequestExecution mockExecution = mock(ClientHttpRequestExecution.class);
        final ClientHttpResponse mockResponse = mock(ClientHttpResponse.class);
        final byte[] body = new byte[0];
        doReturn(mockHeaders).when(mockRequest).getHeaders();
        doReturn(mockResponse).when(mockExecution).execute(any(HttpRequest.class), any(byte[].class));

        final ClientHttpResponse response = interceptor.intercept(mockRequest, body, mockExecution);

        verify(mockRequest).getHeaders();
        verify(mockHeaders).add("Authentication", "MDphYzdhZTIyOC1lMTJlLTExZTQtOGM4ZC01ZjczYmZkZGY2ZmM6Z3pBVGp4MWdDVndoQTJ4R282dlE2RE02TkVmZ2RrTWJVNGFP");
        verify(mockExecution).execute(mockRequest, body);
        assertThat(response).isSameAs(mockResponse);
    }
}