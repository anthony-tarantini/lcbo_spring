package com.tarantini.lcbo.alkyhols;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class AlkyholsControllerTest {
    private AlkyholsController mAlkyholsController;
    @Mock
    private AlkyholsService mAlkyholsService;

    @Before
    public void setup() {
        initMocks(this);
        mAlkyholsController = new AlkyholsController(mAlkyholsService);
    }

    @Test
    public void requestMapping_getAlkyhols() throws Exception {
        doReturn(new AlkyholsResponse()).when(mAlkyholsService).getAllAlkyhols(anyInt());

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mAlkyholsController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/alkyhols").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAlkyhols_passesThroughToService() {
        final AlkyholsResponse expected = new AlkyholsResponse();
        doReturn(expected).when(mAlkyholsService).getAllAlkyhols(anyInt());

        final AlkyholsResponse response = mAlkyholsController.getAllAlkyhols(1);

        verify(mAlkyholsService).getAllAlkyhols(1);
        assertThat(response).isSameAs(expected);
    }

    @Test
    public void requestMapping_getAlkyhol() throws Exception {
        doReturn(new AlkyholResponse()).when(mAlkyholsService).getAlkyholById(anyInt());

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mAlkyholsController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/alkyhols/123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAlkyholById_passesThroughToService() {
        final AlkyholResponse expected = new AlkyholResponse();
        doReturn(expected).when(mAlkyholsService).getAlkyholById(anyInt());

        final AlkyholResponse response = mAlkyholsController.getAlkyholById(1);

        verify(mAlkyholsService).getAlkyholById(1);
        assertThat(response).isSameAs(expected);
    }
}