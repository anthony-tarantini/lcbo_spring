package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.gateway.Hours;
import com.tarantini.lcbo.domain.gateway.Store;
import com.tarantini.lcbo.domain.lcbo.LcboResponse;
import com.tarantini.lcbo.domain.lcbo.LcboStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class StoresService {
    private final StoresClient mStoresClient;
    private final StoresLinker mStoresLinker;

    @Autowired
    public StoresService(final StoresClient storesClient, final StoresLinker storesLinker) {
        mStoresClient = storesClient;
        mStoresLinker = storesLinker;
    }

    public StoresResponse getStores(final int page) {
        final LcboResponse<List<LcboStore>> lcboResponse = mStoresClient.getStores(page);
        final StoresResponse storesResponse = new StoresResponse(lcboResponse.getResult().stream().map(this::translateStore).collect(Collectors.toList()));
        mStoresLinker.addPagerLinks(storesResponse, lcboResponse.getLcboPager());
        return storesResponse;
    }

    public Store getStore(final int storeId) {
        final LcboResponse<LcboStore> lcboResponse = mStoresClient.getStoreForId(storeId);
        return translateStore(lcboResponse.getResult());
    }

    private Store translateStore(final LcboStore lcboStore) {
        return Store.builder()
                .storeId(lcboStore.getId())
                .name(lcboStore.getName())
                .addressLineOne(lcboStore.getAddressLineOne())
                .addressLineTwo(lcboStore.getAddressLineTwo())
                .city(lcboStore.getCity())
                .postalCode(lcboStore.getPostalCode())
                .telephone(lcboStore.getTelephone())
                .latitude(lcboStore.getLatitude())
                .longitude(lcboStore.getLongitude())
                .hours(generateHours(lcboStore))
                .build();
    }

    private Hours generateHours(final LcboStore lcboStore) {
        return Hours.builder()
                .sunday(createHoursString(lcboStore.getSundayOpen(), lcboStore.getSundayClose()))
                .monday(createHoursString(lcboStore.getMondayOpen(), lcboStore.getMondayClose()))
                .tuesday(createHoursString(lcboStore.getTuesdayOpen(), lcboStore.getTuesdayClose()))
                .wednesday(createHoursString(lcboStore.getWednesdayOpen(), lcboStore.getWednesdayClose()))
                .thursday(createHoursString(lcboStore.getThursdayOpen(), lcboStore.getThursdayClose()))
                .friday(createHoursString(lcboStore.getFridayOpen(), lcboStore.getFridayClose()))
                .saturday(createHoursString(lcboStore.getSaturdayOpen(), lcboStore.getSaturdayClose()))
                .build();
    }

    private String createHoursString(final int open, final int close) {
        return translateTime(open) + " - " + translateTime(close);
    }

    private String translateTime(final int time) {
        return StringUtils.leftPad(String.valueOf(time / 60), 2, "0")
                + ":"
                + StringUtils.leftPad(String.valueOf(time % 60), 2, "0");
    }
}
