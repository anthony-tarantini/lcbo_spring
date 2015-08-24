package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.domain.gateway.Alkyhol;
import com.tarantini.lcbo.domain.gateway.Container;
import com.tarantini.lcbo.domain.gateway.Image;
import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class AlkyholsService {

    private final AlkyholsClient mAlkyholsClient;
    private final AlkyholsLinker mAlkyholsLinker;

    @Autowired
    public AlkyholsService(final AlkyholsClient alkyholsClient, final AlkyholsLinker alkyholsLinker) {
        mAlkyholsClient = alkyholsClient;
        mAlkyholsLinker = alkyholsLinker;
    }

    public AlkyholsResponse getAllAlkyhols(final int page) {
        final LcboResponse<List<LcboProduct>> lcboResponse = mAlkyholsClient.getProducts(page);
        final AlkyholsResponse gatewayResponse = new AlkyholsResponse(lcboResponse.getResult().stream().map(this::translateLcboProductToAlkyhol).collect(Collectors.toList()));
        mAlkyholsLinker.addPagerLinks(gatewayResponse, lcboResponse.getLcboPager());
        return gatewayResponse;
    }

    public AlkyholResponse getAlkyholById(final int productId) {
        final LcboProduct product = mAlkyholsClient.getProductById(productId);
        return AlkyholResponse.builder().alkyhol(translateLcboProductToAlkyhol(product)).build();
    }

    private Alkyhol translateLcboProductToAlkyhol(final LcboProduct lcboProduct) {
        return Alkyhol.builder()
                .id(lcboProduct.getId())
                .name(lcboProduct.getName())
                .price(translatePrice(lcboProduct.getPriceInCents()))
                .category(lcboProduct.getPrimaryCategory())
                .origin(lcboProduct.getOrigin())
                .alcoholContent(translateAlcoholContent(lcboProduct.getAlcoholContent()))
                .producer(lcboProduct.getProducerName())
                .image(translateImage(lcboProduct))
                .container(translateContainer(lcboProduct))
                .build();
    }

    private String translatePrice(final Integer priceInCents) {
        return String.format("$%.2f", priceInCents / 100f);
    }

    private String translateAlcoholContent(final Integer alcoholContent) {
        return String.format("%.1f%%", alcoholContent / 100f);
    }

    private Image translateImage(final LcboProduct lcboProduct) {
        return Image.builder()
                .full(lcboProduct.getImageUrl())
                .thumb(lcboProduct.getImageThumbUrl())
                .build();
    }

    private Container translateContainer(final LcboProduct lcboProduct) {
        return Container.builder()
                .type(lcboProduct.getPackageUnitType())
                .units(lcboProduct.getTotalPackageUnits())
                .volume(translateVolume(lcboProduct.getPackageUnitVolumeInMilliliters()))
                .build();
    }

    private String translateVolume(final Integer volume) {
        if (volume < 1000) {
            return String.format("%d mL", volume);
        }
        return String.format("%.2f L", volume / 1000f);
    }
}
