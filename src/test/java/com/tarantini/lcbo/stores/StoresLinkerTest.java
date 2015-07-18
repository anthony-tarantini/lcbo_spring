package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.lcbo.LcboPager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class StoresLinkerTest {

    private StoresLinker mStoresLinker;
    private StoresResponse mStoresResponse;
    private LcboPager mLcboPager;

    @Before
    public void setup() {
        final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        mStoresLinker = new StoresLinker();
        mStoresResponse = new StoresResponse();
        mLcboPager = new LcboPager();
    }

    @Test
    public void addPagerLinks() {
        mLcboPager.setPreviousPage(1);
        mLcboPager.setCurrentPage(2);
        mLcboPager.setNextPage(3);

        mStoresLinker.addPagerLinks(mStoresResponse, mLcboPager);

        assertThat(mStoresResponse.getLink("prev").getHref()).isEqualTo("http://localhost/stores?page=1");
        assertThat(mStoresResponse.getLink("curr").getHref()).isEqualTo("http://localhost/stores?page=2");
        assertThat(mStoresResponse.getLink("next").getHref()).isEqualTo("http://localhost/stores?page=3");
    }

    @Test
    public void addPagerLinks_withNulls() {
        mLcboPager.setPreviousPage(null);
        mLcboPager.setCurrentPage(1);
        mLcboPager.setNextPage(null);

        mStoresLinker.addPagerLinks(mStoresResponse, mLcboPager);

        assertThat(mStoresResponse.getLink("prev")).isNull();
        assertThat(mStoresResponse.getLink("curr").getHref()).isEqualTo("http://localhost/stores?page=1");
        assertThat(mStoresResponse.getLink("next")).isNull();
    }
}