package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.PagerLinker;
import com.tarantini.lcbo.domain.gateway.Link;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class AlkyholsLinker extends PagerLinker {
    @Override
    protected void createLinkToPage(final LinkResponse response, final Integer page, final String rel) {
        response.addLink(new Link(rel, "alkyhols?page=" + page));
    }
}
