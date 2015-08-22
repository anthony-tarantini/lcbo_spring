package com.tarantini.lcbo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
class ProductsController {

    private final ProductsService mProductsService;

    @Autowired
    public ProductsController(final ProductsService productsService) {
        mProductsService = productsService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductsResponse getProducts(@RequestParam(required = false, defaultValue = "1") final int page) {
        return mProductsService.getProducts(page);
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProductById(@PathVariable final Integer productId) {
        return mProductsService.getProductById(productId);
    }
}
