package com.tarantini.lcbo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
            final HttpRequest request,
            final byte[] body,
            final ClientHttpRequestExecution execution
    ) throws IOException {
        final HttpHeaders headers = request.getHeaders();
        headers.add("Authentication", "MDphYzdhZTIyOC1lMTJlLTExZTQtOGM4ZC01ZjczYmZkZGY2ZmM6Z3pBVGp4MWdDVndoQTJ4R282dlE2RE02TkVmZ2RrTWJVNGFP");
        return execution.execute(request, body);
    }
}
