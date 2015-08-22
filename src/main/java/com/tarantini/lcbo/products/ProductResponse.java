package com.tarantini.lcbo.products;

import com.tarantini.lcbo.domain.gateway.Product;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
class ProductResponse extends ResourceSupport {
    private Product product;
}
