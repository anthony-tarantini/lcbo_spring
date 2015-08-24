package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.gateway.Link;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import org.junit.Test;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StoresLinkerTest {

    @Test
    public void createPagerLink() {
        final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        final StoresLinker storesLinker = new StoresLinker();

        final LinkResponse response = mock(LinkResponse.class);
        storesLinker.createLinkToPage(response, 1, "test");

        verify(response).addLink(new Link("test", "/stores/page=1"));
    }
}