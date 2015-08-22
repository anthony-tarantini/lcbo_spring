package com.tarantini.lcbo.products;

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
public class ProductsControllerTest {
    private ProductsController mProductsController;
    @Mock
    private ProductsService mProductsService;

    @Before
    public void setup() {
        initMocks(this);
        mProductsController = new ProductsController(mProductsService);
    }

    @Test
    public void requestMapping_getProducts() throws Exception {
        doReturn(new ProductsResponse()).when(mProductsService).getProducts(anyInt());

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mProductsController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getProducts_passesThroughToService() {
        final ProductsResponse expected = new ProductsResponse();
        doReturn(expected).when(mProductsService).getProducts(anyInt());

        final ProductsResponse response = mProductsController.getProducts(1);

        verify(mProductsService).getProducts(1);
        assertThat(response).isSameAs(expected);
    }

    @Test
    public void requestMapping_getProduct() throws Exception {
        doReturn(new ProductResponse()).when(mProductsService).getProductById(anyInt());

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mProductsController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/products/123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getProductById_passesThroughToService() {
        final ProductResponse expected = new ProductResponse();
        doReturn(expected).when(mProductsService).getProductById(anyInt());

        final ProductResponse response = mProductsController.getProductById(1);

        verify(mProductsService).getProductById(1);
        assertThat(response).isSameAs(expected);
    }
}