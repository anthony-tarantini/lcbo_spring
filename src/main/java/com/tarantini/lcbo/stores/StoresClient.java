package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.LcboResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class StoresClient {
    private final RestTemplate mRestTemplate;

    @Autowired
    public StoresClient(final RestTemplate restTemplate) {
        mRestTemplate = restTemplate;
    }

    public LcboStore[] getStores() {
        final ParameterizedTypeReference<LcboResponse<LcboStore[]>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore[]>>(){};

        final ResponseEntity<LcboResponse<LcboStore[]>> entity =
                mRestTemplate.exchange("http://lcboapi.com/stores", HttpMethod.GET, new HttpEntity(null), responseType);

        return entity.getBody().getResult();
    }

    public LcboStore getStoreForId(final int storeId) {
        final ParameterizedTypeReference<LcboResponse<LcboStore>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore>>(){};

        final ResponseEntity<LcboResponse<LcboStore>> entity =
                mRestTemplate.exchange("http://lcboapi.com/stores/{id}", HttpMethod.GET, new HttpEntity(null), responseType, storeId);

        return entity.getBody().getResult();
    }
}
