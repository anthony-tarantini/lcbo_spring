package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
class ProductsClient {
    private final RestTemplate mRestTemplate;

    @Autowired
    public ProductsClient(final RestTemplate restTemplate) {
        mRestTemplate = restTemplate;
    }

    public LcboResponse<List<LcboProduct>> getProducts(final int page) {
        final ParameterizedTypeReference<LcboResponse<List<LcboProduct>>> responseType = new ParameterizedTypeReference<LcboResponse<List<LcboProduct>>>() {
        };

        final ResponseEntity<LcboResponse<List<LcboProduct>>> entity =
                mRestTemplate.exchange("http://lcboapi.com/products?page={page}", HttpMethod.GET, new HttpEntity(null), responseType, page);

        return entity.getBody();
    }
}
