package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductsClientTest {
    private ProductsClient mProductsClient;
    @Mock
    private RestTemplate mRestTemplate;

    @Before
    public void setup() {
        initMocks(this);
        mProductsClient = new ProductsClient(mRestTemplate);
    }

    @Test
    public void getStores_makesCall() {
        final LcboResponse<List<LcboProduct>> lcboResponse = new LcboResponse<List<LcboProduct>>();
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboResponse<List<LcboProduct>> products = mProductsClient.getProducts(1);

        final ParameterizedTypeReference<LcboResponse<List<LcboProduct>>> responseType = new ParameterizedTypeReference<LcboResponse<List<LcboProduct>>>() {
        };
        verify(mRestTemplate).exchange("http://lcboapi.com/products?page={page}", HttpMethod.GET, new HttpEntity(null), responseType, 1);
        assertThat(products).isSameAs(lcboResponse);
    }

}