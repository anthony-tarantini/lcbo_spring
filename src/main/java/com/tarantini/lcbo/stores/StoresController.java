package com.tarantini.lcbo.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
class StoresController {
    private final StoresService mStoresService;

    @Autowired
    public StoresController(final StoresService storesService) {
        mStoresService = storesService;
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StoresResponse getStores(@RequestParam(required = false, defaultValue = "1") final int page) {
        return mStoresService.getStores(page);
    }

    @RequestMapping(value = "/stores/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StoreResponse getStoreById(@PathVariable int storeId) {
        return new StoreResponse(mStoresService.getStore(storeId));
    }
}
