package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.GatewayResponse;
import com.tarantini.lcbo.domain.gateway.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
class StoresResponse extends GatewayResponse {
    private List<Store> stores;
}