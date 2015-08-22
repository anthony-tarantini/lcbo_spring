package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.PagerLinker;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class StoresLinker extends PagerLinker {
    @Override
    protected void createLinkToPage(final ResourceSupport response, final Integer page, final String rel) {
        response.add(linkTo(methodOn(StoresController.class).getStores(page)).withRel(rel));
    }
}
