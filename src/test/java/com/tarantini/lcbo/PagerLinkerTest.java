package com.tarantini.lcbo;

import com.tarantini.lcbo.domain.gateway.LinkResponse;
import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;

import static org.mockito.Mockito.*;

public class PagerLinkerTest {

    private TestPagerLinker mLinker;
    private LinkResponse mResponse;

    @Before
    public void setup() {
        mLinker = spy(new TestPagerLinker());
        mResponse = mock(LinkResponse.class);
    }

    @Test
    public void addPageLinks() {
        mLinker.addPagerLinks(mResponse, LcboPager.builder().currentPage(2).previousPage(1).nextPage(3).build());

        verify(mLinker).createLinkToPage(mResponse, 1, Link.REL_PREVIOUS);
        verify(mLinker).createLinkToPage(mResponse, 2, Link.REL_SELF);
        verify(mLinker).createLinkToPage(mResponse, 3, Link.REL_NEXT);
    }

    @Test
    public void doesNotAddPageLinks_whenPagerIsHasNulls() {
        mLinker.addPagerLinks(mResponse, new LcboPager());

        verify(mLinker, never()).createLinkToPage(any(LinkResponse.class), any(Integer.class), anyString());
    }

    private static class TestPagerLinker extends PagerLinker {
        @Override
        protected void createLinkToPage(final LinkResponse response, final Integer page, final String rel) {
        }
    }
}