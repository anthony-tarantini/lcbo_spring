package com.tarantini.lcbo;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.springframework.hateoas.ResourceSupport;

public abstract class PagerLinker {
    protected abstract void createLinkToPage(final ResourceSupport response, final Integer page, final String rel);

    public void addPagerLinks(final ResourceSupport response, final LcboPager lcboPager) {
        addPageIfNotNull(response, lcboPager.getPreviousPage(), "prev");
        addPageIfNotNull(response, lcboPager.getCurrentPage(), "curr");
        addPageIfNotNull(response, lcboPager.getNextPage(), "next");
    }

    private void addPageIfNotNull(final ResourceSupport response, final Integer page, final String rel) {
        if (page == null) {
            return;
        }
        createLinkToPage(response, page, rel);
    }
}
