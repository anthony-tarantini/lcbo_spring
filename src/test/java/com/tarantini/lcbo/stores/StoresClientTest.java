package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import com.tarantini.lcbo.domain.lcbo.LcboStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StoresClientTest {
    private StoresClient mStoresClient;
    @Mock private RestTemplate mRestTemplate;

    @Before
    public void setup() {
        initMocks(this);
        mStoresClient = new StoresClient(mRestTemplate);
    }

    @Test
    public void getStores_makesCall() {
        final LcboResponse<List<LcboStore>> lcboResponse = new LcboResponse<List<LcboStore>>();
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboResponse<List<LcboStore>> storeForId = mStoresClient.getStores(1);

        final ParameterizedTypeReference<LcboResponse<List<LcboStore>>> responseType = new ParameterizedTypeReference<LcboResponse<List<LcboStore>>>() {};
        verify(mRestTemplate).exchange("http://lcboapi.com/stores?page={page}", HttpMethod.GET, new HttpEntity(null), responseType, 1);
        assertThat(storeForId).isSameAs(lcboResponse);
    }

    @Test
    public void getStoreForId_makesCall() {
        final LcboResponse<LcboStore> lcboResponse = new LcboResponse<LcboStore>();
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboResponse storeForId = mStoresClient.getStoreForId(1234);

        final ParameterizedTypeReference<LcboResponse<LcboStore>> responseType = new ParameterizedTypeReference<LcboResponse<LcboStore>>() {};
        verify(mRestTemplate).exchange("http://lcboapi.com/stores/{id}", HttpMethod.GET, new HttpEntity(null), responseType, 1234);
        assertThat(storeForId).isSameAs(lcboResponse);
    }
}