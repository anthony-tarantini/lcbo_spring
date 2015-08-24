package com.tarantini.lcbo;

import com.tarantini.lcbo.domain.gateway.LinkResponse;
import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.springframework.hateoas.Link;

public abstract class PagerLinker {
    protected abstract void createLinkToPage(final LinkResponse response, final Integer page, final String rel);

    public void addPagerLinks(final LinkResponse response, final LcboPager lcboPager) {
        addPageIfNotNull(response, lcboPager.getPreviousPage(), Link.REL_PREVIOUS);
        addPageIfNotNull(response, lcboPager.getCurrentPage(), Link.REL_SELF);
        addPageIfNotNull(response, lcboPager.getNextPage(), Link.REL_NEXT);
    }

    private void addPageIfNotNull(final LinkResponse response, final Integer page, final String rel) {
        if (page == null) {
            return;
        }
        createLinkToPage(response, page, rel);
    }
}
