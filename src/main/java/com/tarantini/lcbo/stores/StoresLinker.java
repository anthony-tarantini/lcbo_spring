package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class StoresLinker {

    public void addPagerLinks(final StoresResponse storesResponse, final LcboPager lcboPager) {
        addPageIfNotNull(storesResponse, lcboPager.getPreviousPage(), "prev");
        addPageIfNotNull(storesResponse, lcboPager.getCurrentPage(), "curr");
        addPageIfNotNull(storesResponse, lcboPager.getNextPage(), "next");
    }

    private void addPageIfNotNull(final StoresResponse storesResponse, final Integer page, final String rel) {
        if (page == null) {
            return;
        }
        storesResponse.add(linkTo(methodOn(StoresController.class).getStores(page)).withRel(rel));
    }
}
