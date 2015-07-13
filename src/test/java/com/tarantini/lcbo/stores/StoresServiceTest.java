package com.tarantini.lcbo.stores;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StoresServiceTest {
    private StoresService mStoresService;
    private StoresClient mMockStoresClient;

    @Before
    public void setup() {
        mMockStoresClient = mock(StoresClient.class);
        mStoresService = new StoresService(mMockStoresClient);
        doReturn(LcboStore.builder().build()).when(mMockStoresClient).getStoreForId(anyInt());
        doReturn(new LcboStore[0]).when(mMockStoresClient).getStores();
    }

    @Test
    public void getStores_runsClient(){
        mStoresService.getStores();

        verify(mMockStoresClient).getStores();
    }

    @Test
    public void getStores_returnsTranslatesStores() {
        final LcboStore[] lcboStores = {createLcboStore(1), createLcboStore(2)};
        doReturn(lcboStores).when(mMockStoresClient).getStores();

        final Store[] stores = mStoresService.getStores();

        assertThat(stores[0].getStoreId()).isEqualTo(1);
        assertThat(stores[1].getStoreId()).isEqualTo(2);
    }

    @Test
    public void findStoreById_runsClient() {
        mStoresService.getStore(1234);

        verify(mMockStoresClient).getStoreForId(1234);
    }

    @Test
    public void findStoreById_returnsTranslatedStore() {
        final LcboStore lcboStore = createLcboStore(1234);
        doReturn(lcboStore).when(mMockStoresClient).getStoreForId(anyInt());

        final Store store = mStoresService.getStore(1234);

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