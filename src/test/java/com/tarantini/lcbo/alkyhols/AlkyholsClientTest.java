package com.tarantini.lcbo.alkyhols;

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

public class AlkyholsClientTest {
    private AlkyholsClient mAlkyholsClient;
    @Mock
    private RestTemplate mRestTemplate;

    @Before
    public void setup() {
        initMocks(this);
        mAlkyholsClient = new AlkyholsClient(mRestTemplate);
    }

    @Test
    public void getProducts_makesCall() {
        final LcboResponse<List<LcboProduct>> lcboResponse = new LcboResponse<List<LcboProduct>>();
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboResponse<List<LcboProduct>> products = mAlkyholsClient.getProducts(1);

        final ParameterizedTypeReference<LcboResponse<List<LcboProduct>>> responseType = new ParameterizedTypeReference<LcboResponse<List<LcboProduct>>>() {
        };
        verify(mRestTemplate).exchange("http://lcboapi.com/products?page={page}", HttpMethod.GET, new HttpEntity(null), responseType, 1);
        assertThat(products).isSameAs(lcboResponse);
    }

    @Test
    public void getProductById_makesCall() {
        final LcboResponse<LcboProduct> lcboResponse = new LcboResponse<LcboProduct>();
        final LcboProduct expected = new LcboProduct();
        lcboResponse.setResult(expected);
        final ResponseEntity responseEntity = mock(ResponseEntity.class);
        doReturn(lcboResponse).when(responseEntity).getBody();
        doReturn(responseEntity).when(mRestTemplate)
                .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class), anyInt());

        final LcboProduct product = mAlkyholsClient.getProductById(1);

        final ParameterizedTypeReference<LcboResponse<LcboProduct>> responseType = new ParameterizedTypeReference<LcboResponse<LcboProduct>>() {
        };
        verify(mRestTemplate).exchange("http://lcboapi.com/products/{productId}", HttpMethod.GET, new HttpEntity(null), responseType, 1);
        assertThat(product).isSameAs(expected);
    }
}