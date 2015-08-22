package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.gateway.Container;
import com.tarantini.lcbo.domain.gateway.Image;
import com.tarantini.lcbo.domain.gateway.Product;
import com.tarantini.lcbo.domain.lcbo.LcboPager;
import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
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
        final List<LcboProduct> lcboProducts = Collections.singletonList(createLcboProduct(1));
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
        final Product productOne = productsResponse.getProducts().get(0);
        assertThat(productOne.getId()).isEqualTo(1);
        assertThat(productOne.getName()).isEqualTo("Blue");
        assertThat(productOne.getPrice()).isEqualTo("$4.46");
        assertThat(productOne.getCategory()).isEqualTo("Beer");
        assertThat(productOne.getOrigin()).isEqualTo("Canada");
        assertThat(productOne.getAlcoholContent()).isEqualTo("5.1%");
        assertThat(productOne.getProducer()).isEqualTo("Labbatt");
        assertThat(productOne.getImage()).isEqualTo(
                Image.builder().full("imageFull").thumb("imageThumb").build()
        );
        assertThat(productOne.getContainer()).isEqualTo(
                Container.builder().volume("1.20 L").type("Can").units(6).build()
        );
    }

    @Test
    public void getProduct() {
        doReturn(createLcboProduct(1)).when(mProductsClient).getProductById(anyInt());

        final ProductResponse productResponse = mProductsService.getProductById(1);

        verify(mProductsClient).getProductById(1);
        final Product productOne = productResponse.getProduct();
        assertThat(productOne.getId()).isEqualTo(1);
        assertThat(productOne.getName()).isEqualTo("Blue");
        assertThat(productOne.getPrice()).isEqualTo("$4.46");
        assertThat(productOne.getCategory()).isEqualTo("Beer");
        assertThat(productOne.getOrigin()).isEqualTo("Canada");
        assertThat(productOne.getAlcoholContent()).isEqualTo("5.1%");
        assertThat(productOne.getProducer()).isEqualTo("Labbatt");
        assertThat(productOne.getImage()).isEqualTo(
                Image.builder().full("imageFull").thumb("imageThumb").build()
        );
        assertThat(productOne.getContainer()).isEqualTo(
                Container.builder().volume("1.20 L").type("Can").units(6).build()
        );
    }

    private LcboProduct createLcboProduct(final int id) {
        return LcboProduct.builder()
                .id(id)
                .name("Blue")
                .priceInCents(446)
                .primaryCategory("Beer")
                .origin("Canada")
                .alcoholContent(510)
                .producerName("Labbatt")
                .imageThumbUrl("imageThumb")
                .imageUrl("imageFull")
                .packageUnitVolumeInMilliliters(1200)
                .packageUnitType("Can")
                .totalPackageUnits(6)
                .build();
    }
}