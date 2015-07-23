package com.tarantini.lcbo;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PagerLinkerTest {

    private TestPagerLinker mLinker;
    private GatewayResponse mResponse;

    @Before
    public void setup() {
        mLinker = spy(new TestPagerLinker());
        mResponse = mock(GatewayResponse.class);
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

        verify(mLinker, never()).createLinkToPage(any(GatewayResponse.class), any(Integer.class), anyString());
    }

    private static class TestPagerLinker extends PagerLinker {
        @Override
        protected void createLinkToPage(final GatewayResponse response, final Integer page, final String rel) {
        }
    }
}