package com.tarantini.lcbo.stores;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StoresService {
    private final StoresClient mStoresClient;

    @Autowired
    public StoresService(final StoresClient storesClient) {
        mStoresClient = storesClient;
    }

    public Store[] getStores() {
        final LcboStore[] lcboStores = mStoresClient.getStores();
        final Store[] stores = new Store[lcboStores.length];
        for (int i = 0; i < lcboStores.length; i++) {
            stores[i] = translateStore(lcboStores[i]);
        }
        return stores;
    }

    public Store getStore(final int storeId) {
        final LcboStore lcboStore = mStoresClient.getStoreForId(storeId);

        return translateStore(lcboStore);
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
