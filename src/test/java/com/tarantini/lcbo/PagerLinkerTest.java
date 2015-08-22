package com.tarantini.lcbo;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.ResourceSupport;

import static org.mockito.Mockito.*;

public class PagerLinkerTest {

    private TestPagerLinker mLinker;
    private ResourceSupport mResponse;

    @Before
    public void setup() {
        mLinker = spy(new TestPagerLinker());
        mResponse = mock(ResourceSupport.class);
    }

    @Test
    public void addPageLinks() {
        mLinker.addPagerLinks(mResponse, LcboPager.builder().currentPage(2).previousPage(1).nextPage(3).build());

        verify(mLinker).createLinkToPage(mResponse, 1, "prev");
        verify(mLinker).createLinkToPage(mResponse, 2, "curr");
        verify(mLinker).createLinkToPage(mResponse, 3, "next");
    }

    @Test
    public void doesNotAddPageLinks_whenPagerIsHasNulls() {
        mLinker.addPagerLinks(mResponse, new LcboPager());

        verify(mLinker, never()).createLinkToPage(any(ResourceSupport.class), any(Integer.class), anyString());
    }

    private static class TestPagerLinker extends PagerLinker {
        @Override
        protected void createLinkToPage(final ResourceSupport response, final Integer page, final String rel) {
        }
    }
}