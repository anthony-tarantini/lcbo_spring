package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.LcboResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StoresClientTest {
    private StoresClient mStoresClient;
    private RestTemplate mMockRestTemplate;

    @Before
    public void setup() {
        mMockRestTemplate = mock(RestTemplate.class);
        mStoresClient = new StoresClient(mMockRestTemplate);
    }

    @Test
    public void getStores_makesCall() {
        final LcboResponse<LcboStore[]> lcboResponse = new LcboResponse<LcboStore[]>();
        final LcboStore[] result = new LcboStore[0];
        lcboResponse.setResult(result);
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mMockRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class));

        final LcboStore[] storeForId = mStoresClient.getStores();

        final ParameterizedTypeReference<LcboResponse<LcboStore[]>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore[]>>() {};
        verify(mMockRestTemplate).exchange("http://lcboapi.com/stores", HttpMethod.GET, new HttpEntity(null), responseType);
        assertThat(storeForId).isSameAs(result);
    }

    @Test
    public void getStoreForId_makesCall() {
        final LcboResponse<LcboStore> lcboResponse = new LcboResponse<LcboStore>();
        final LcboStore result = LcboStore.builder().build();
        lcboResponse.setResult(result);
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mMockRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboStore storeForId = mStoresClient.getStoreForId(1234);

        final ParameterizedTypeReference<LcboResponse<LcboStore>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore>>() {};
        verify(mMockRestTemplate).exchange("http://lcboapi.com/stores/{id}", HttpMethod.GET, new HttpEntity(null), responseType, 1234);
        assertThat(storeForId).isSameAs(result);
    }
}