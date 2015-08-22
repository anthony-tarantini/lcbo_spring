package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.gateway.Container;
import com.tarantini.lcbo.domain.gateway.Image;
import com.tarantini.lcbo.domain.gateway.Product;
import com.tarantini.lcbo.domain.lcbo.LcboProduct;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductsService {

    private final ProductsClient mProductsClient;
    private final ProductsLinker mProductsLinker;

    @Autowired
    public ProductsService(final ProductsClient productsClient, final ProductsLinker productsLinker) {
        mProductsClient = productsClient;
        mProductsLinker = productsLinker;
    }

    public ProductsResponse getProducts(final int page) {
        final LcboResponse<List<LcboProduct>> lcboResponse = mProductsClient.getProducts(page);
        final ProductsResponse gatewayResponse = new ProductsResponse(lcboResponse.getResult().stream().map(this::translateProduct).collect(Collectors.toList()));
        mProductsLinker.addPagerLinks(gatewayResponse, lcboResponse.getLcboPager());
        return gatewayResponse;
    }

    public ProductResponse getProductById(final int productId) {
        final LcboProduct product = mProductsClient.getProductById(productId);
        return ProductResponse.builder().product(translateProduct(product)).build();
    }

    private Product translateProduct(final LcboProduct lcboProduct) {
        return Product.builder()
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
