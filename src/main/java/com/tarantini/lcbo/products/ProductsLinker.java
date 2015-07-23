package com.tarantini.lcbo.products;

import com.tarantini.lcbo.GatewayResponse;
import com.tarantini.lcbo.PagerLinker;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

class ProductsLinker extends PagerLinker {
    @Override
    protected void createLinkToPage(final GatewayResponse response, final Integer page, final String rel) {
        response.add(linkTo(methodOn(ProductsController.class).getProducts(page)).withRel(rel));
    }
}
