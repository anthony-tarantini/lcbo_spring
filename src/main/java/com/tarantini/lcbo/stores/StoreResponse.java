package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.gateway.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class StoreResponse {
    private Store store;
}
