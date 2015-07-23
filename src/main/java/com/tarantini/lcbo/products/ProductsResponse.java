package com.tarantini.lcbo.products;

import com.tarantini.lcbo.GatewayResponse;
import com.tarantini.lcbo.domain.gateway.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ProductsResponse extends GatewayResponse {
    private List<Product> products;
}
