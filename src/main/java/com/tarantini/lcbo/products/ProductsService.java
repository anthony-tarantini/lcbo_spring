package com.tarantini.lcbo.products;

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

    private Product translateProduct(final LcboProduct lcboProduct) {
        return Product.builder().id(lcboProduct.getId()).build();
    }
}
