package com.tarantini.lcbo.products;

import com.tarantini.lcbo.GatewayResponse;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductsLinkerTest {

    @Test
    public void createPagerLink() {
        final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        final ProductsLinker productsLinker = new ProductsLinker();

        final GatewayResponse response = mock(GatewayResponse.class);
        productsLinker.createLinkToPage(response, 1, "test");

        verify(response).add(linkTo(methodOn(ProductsController.class).getProducts(1)).withRel("test"));
    }
}