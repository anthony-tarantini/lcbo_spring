package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import com.tarantini.lcbo.domain.lcbo.LcboStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
class StoresClient {
    private final RestTemplate mRestTemplate;

    @Autowired
    public StoresClient(final RestTemplate restTemplate) {
        mRestTemplate = restTemplate;
    }

    public LcboResponse<List<LcboStore>> getStores(final int page) {
        final ParameterizedTypeReference<LcboResponse<List<LcboStore>>> responseType = new ParameterizedTypeReference<LcboResponse<List<LcboStore>>>() {
        };

        final ResponseEntity<LcboResponse<List<LcboStore>>> entity =
                mRestTemplate.exchange("http://lcboapi.com/stores?page={page}", HttpMethod.GET, new HttpEntity(null), responseType, page);

        return entity.getBody();
    }

    public LcboResponse<LcboStore> getStoreForId(final int storeId) {
        final ParameterizedTypeReference<LcboResponse<LcboStore>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore>>() {
        };

        final ResponseEntity<LcboResponse<LcboStore>> entity =
                mRestTemplate.exchange("http://lcboapi.com/stores/{id}", HttpMethod.GET, new HttpEntity(null), responseType, storeId);

        return entity.getBody();
    }
}
