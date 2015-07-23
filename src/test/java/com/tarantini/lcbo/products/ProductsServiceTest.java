package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductsServiceTest {
    private ProductsService mProductsService;
    @Mock
    private ProductsClient mProductsClient;
    @Mock
    private ProductsLinker mProductsLinker;

    @Before
    public void setup() {
        initMocks(this);
        mProductsService = new ProductsService(mProductsClient, mProductsLinker);
    }

    @Test
    public void getProducts() {
        final List<LcboProduct> lcboProducts = Arrays.asList(createLcboProduct(1), createLcboProduct(2));
        final LcboResponse<List<LcboProduct>> response = new LcboResponse<List<LcboProduct>>();
        response.setResult(lcboProducts);
        final LcboPager lcboPager = new LcboPager();
        lcboPager.setPreviousPage(0);
        lcboPager.setCurrentPage(1);
        lcboPager.setNextPage(2);
        response.setLcboPager(lcboPager);
        doReturn(response).when(mProductsClient).getProducts(anyInt());

        final ProductsResponse productsResponse = mProductsService.getProducts(1);

        verify(mProductsClient).getProducts(1);
        verify(mProductsLinker).addPagerLinks(productsResponse, lcboPager);
        assertThat(productsResponse.getProducts().get(0).getId()).isEqualTo(1);
        assertThat(productsResponse.getProducts().get(1).getId()).isEqualTo(2);
    }

    private LcboProduct createLcboProduct(final int id) {
        return LcboProduct.builder().id(id).build();
    }
}