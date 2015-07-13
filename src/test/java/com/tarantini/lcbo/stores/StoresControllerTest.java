package com.tarantini.lcbo.stores;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.tarantini.lcbo.stores.StoresController.StoreResponse;
import static com.tarantini.lcbo.stores.StoresController.StoresResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class StoresControllerTest {

    private StoresService mMockStoresService;
    private StoresController mStoresController;

    @Before
    public void setup() {
        mMockStoresService = mock(StoresService.class);
        mStoresController = new StoresController(mMockStoresService);
    }

    @Test
    public void requestMapping_getStoreById() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mStoresController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/stores/1234").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getStoreById_passesThroughToService() {
        final Store returnedStore = Store.builder().build();
        doReturn(returnedStore).when(mMockStoresService).getStore(anyInt());

        final StoreResponse response = mStoresController.getStoreById(1234);

        verify(mMockStoresService).getStore(1234);
        assertThat(response.getStore()).isSameAs(returnedStore);
    }

    @Test
    public void requestMapping_getStores() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mStoresController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/stores").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getStores_passesThroughToService() {
        final Store[] returnedStores = new Store[2];
        doReturn(returnedStores).when(mMockStoresService).getStores();

        final StoresResponse response = mStoresController.getStores();

        verify(mMockStoresService).getStores();
        assertThat(response.getStores()).isSameAs(returnedStores);
    }
}