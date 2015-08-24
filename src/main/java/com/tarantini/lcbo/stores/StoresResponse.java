package com.tarantini.lcbo.stores;

import com.tarantini.lcbo.domain.gateway.LinkResponse;
import com.tarantini.lcbo.domain.gateway.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
class StoresResponse extends LinkResponse {
    private List<Store> stores;
}