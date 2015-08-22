package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.gateway.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ProductsResponse extends ResourceSupport {
    private List<Product> products;
}
