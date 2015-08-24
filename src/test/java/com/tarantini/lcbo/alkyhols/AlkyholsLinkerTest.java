package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.domain.gateway.Link;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AlkyholsLinkerTest {

    @Test
    public void createPagerLink() {
        final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        final AlkyholsLinker alkyholsLinker = new AlkyholsLinker();

        final LinkResponse response = mock(LinkResponse.class);
        alkyholsLinker.createLinkToPage(response, 1, "test");

        verify(response).addLink(new Link("test", "alkyhols/page=1"));
    }
}