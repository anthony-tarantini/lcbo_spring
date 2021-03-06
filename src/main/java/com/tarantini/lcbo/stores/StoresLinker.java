package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.PagerLinker;
import com.tarantini.lcbo.domain.gateway.Link;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import org.springframework.stereotype.Component;

@Component
class StoresLinker extends PagerLinker {
    @Override
    protected void createLinkToPage(final LinkResponse response, final Integer page, final String rel) {
        response.addLink(new Link(rel, "/stores?page=" + page));
    }
}
