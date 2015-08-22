package com.tarantini.lcbo.products;

import com.tarantini.lcbo.PagerLinker;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ProductsLinker extends PagerLinker {
    @Override
    protected void createLinkToPage(final ResourceSupport response, final Integer page, final String rel) {
        response.add(linkTo(methodOn(ProductsController.class).getProducts(page)).withRel(rel));
    }
}
