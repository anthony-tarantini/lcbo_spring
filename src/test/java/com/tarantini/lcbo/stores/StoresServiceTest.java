package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.gateway.Hours;
import com.tarantini.lcbo.domain.gateway.Store;
import com.tarantini.lcbo.domain.lcbo.LcboPager;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import com.tarantini.lcbo.domain.lcbo.LcboStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class StoresServiceTest {
    private StoresService mStoresService;
    @Mock private StoresClient mStoresClient;
    @Mock private StoresLinker mStoresLinker;

    @Before
    public void setup() {
        initMocks(this);
        mStoresService = new StoresService(mStoresClient, mStoresLinker);
    }

    @Test
    public void getStores(){
        final List<LcboStore> lcboStores = Arrays.asList(createLcboStore(1), createLcboStore(2));
        final LcboResponse<List<LcboStore>> response = new LcboResponse<List<LcboStore>>();
        response.setResult(lcboStores);
        final LcboPager lcboPager = new LcboPager();
        lcboPager.setPreviousPage(0);
        lcboPager.setCurrentPage(1);
        lcboPager.setNextPage(2);
        response.setLcboPager(lcboPager);
        doReturn(response).when(mStoresClient).getStores(anyInt());

        final StoresResponse storesResponse = mStoresService.getStores(0);

        verify(mStoresClient).getStores(0);
        verify(mStoresLinker).addPagerLinks(storesResponse, lcboPager);
        assertThat(storesResponse.getStores().get(0).getStoreId()).isEqualTo(1);
        assertThat(storesResponse.getStores().get(1).getStoreId()).isEqualTo(2);
    }

    @Test
    public void findStoreById_runsClient() {
        final LcboStore lcboStore = createLcboStore(1234);
        final LcboResponse<LcboStore> lcboResponse = new LcboResponse<LcboStore>();
        lcboResponse.setResult(lcboStore);
        doReturn(lcboResponse).when(mStoresClient).getStoreForId(anyInt());

        final Store store = mStoresService.getStore(1234);

        verify(mStoresClient).getStoreForId(1234);
        final Hours hours = Hours.builder()
                .sunday("11:00 - 18:00")
                .monday("09:00 - 22:00")
                .tuesday("09:00 - 22:00")
                .wednesday("09:00 - 22:00")
                .thursday("09:00 - 22:00")
                .friday("09:00 - 22:00")
                .saturday("10:00 - 21:00")
                .build();
        final Store expectedStore = Store.builder()
                .storeId(1234)
                .name("test store")
                .addressLineOne("test address one")
                .addressLineTwo("test address two")
                .city("test city")
                .postalCode("test code")
                .telephone("test phone")
                .latitude(43.643f)
                .longitude(-79.3723f)
                .hours(hours)
                .build();
        assertThat(store).isEqualTo(expectedStore);
    }

    private LcboStore createLcboStore(final int id) {
        return LcboStore.builder()
                .id(id)
                .isDead(false)
                .name("test store")
                .addressLineOne("test address one")
                .addressLineTwo("test address two")
                .city("test city")
                .postalCode("test code")
                .telephone("test phone")
                .latitude(43.643f)
                .longitude(-79.3723f)
                .sundayOpen(660).sundayClose(1080)
                .mondayOpen(540).mondayClose(1320)
                .tuesdayOpen(540).tuesdayClose(1320)
                .wednesdayOpen(540).wednesdayClose(1320)
                .thursdayOpen(540).thursdayClose(1320)
                .fridayOpen(540).fridayClose(1320)
                .saturdayOpen(600).saturdayClose(1260)
                .build();
    }
}