package com.tarantini.lcbo.stores;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StoresController {
    private final StoresService mStoresService;

    @Autowired
    public StoresController(final StoresService storesService) {
        mStoresService = storesService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StoresResponse getStores(){
        return new StoresResponse(mStoresService.getStores());
    }

    @RequestMapping(value = "/stores/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StoreResponse getStoreById(@PathVariable int storeId) {
        return new StoreResponse(mStoresService.getStore(storeId));
    }

    @Data
    @AllArgsConstructor
    public static class StoreResponse {
        private Store store;
    }

    @Data
    @AllArgsConstructor
    public static class StoresResponse {
        private Store[] stores;
    }
}
