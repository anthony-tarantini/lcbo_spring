package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.domain.gateway.Alkyhol;
import com.tarantini.lcbo.domain.gateway.Container;
import com.tarantini.lcbo.domain.gateway.Image;
import com.tarantini.lcbo.domain.lcbo.LcboPager;
import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlkyholsServiceTest {
    private AlkyholsService mAlkyholsService;
    @Mock
    private AlkyholsClient mAlkyholsClient;
    @Mock
    private AlkyholsLinker mAlkyholsLinker;

    @Before
    public void setup() {
        initMocks(this);
        mAlkyholsService = new AlkyholsService(mAlkyholsClient, mAlkyholsLinker);
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
        doReturn(response).when(mAlkyholsClient).getProducts(anyInt());

        final AlkyholsResponse alkyholsResponse = mAlkyholsService.getAllAlkyhols(1);

        verify(mAlkyholsClient).getProducts(1);
        verify(mAlkyholsLinker).addPagerLinks(alkyholsResponse, lcboPager);
        final Alkyhol alkyhol = alkyholsResponse.getAlkyhols().get(0);
        assertThat(alkyhol.getId()).isEqualTo(1);
        assertThat(alkyhol.getName()).isEqualTo("Blue");
        assertThat(alkyhol.getPrice()).isEqualTo("$4.46");
        assertThat(alkyhol.getCategory()).isEqualTo("Beer");
        assertThat(alkyhol.getOrigin()).isEqualTo("Canada");
        assertThat(alkyhol.getAlcoholContent()).isEqualTo("5.1%");
        assertThat(alkyhol.getProducer()).isEqualTo("Labbatt");
        assertThat(alkyhol.getImage()).isEqualTo(
                Image.builder().full("imageFull").thumb("imageThumb").build()
        );
        assertThat(alkyhol.getContainer()).isEqualTo(
                Container.builder().volume("1.20 L").type("Can").units(6).build()
        );
    }

    @Test
    public void getProduct() {
        doReturn(createLcboProduct(1)).when(mAlkyholsClient).getProductById(anyInt());

        final AlkyholResponse alkyholResponse = mAlkyholsService.getAlkyholById(1);

        verify(mAlkyholsClient).getProductById(1);
        final Alkyhol alkyhol = alkyholResponse.getAlkyhol();
        assertThat(alkyhol.getId()).isEqualTo(1);
        assertThat(alkyhol.getName()).isEqualTo("Blue");
        assertThat(alkyhol.getPrice()).isEqualTo("$4.46");
        assertThat(alkyhol.getCategory()).isEqualTo("Beer");
        assertThat(alkyhol.getOrigin()).isEqualTo("Canada");
        assertThat(alkyhol.getAlcoholContent()).isEqualTo("5.1%");
        assertThat(alkyhol.getProducer()).isEqualTo("Labbatt");
        assertThat(alkyhol.getImage()).isEqualTo(
                Image.builder().full("imageFull").thumb("imageThumb").build()
        );
        assertThat(alkyhol.getContainer()).isEqualTo(
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