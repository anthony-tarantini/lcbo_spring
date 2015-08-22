package com.tarantini.lcbo.domain.gateway;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Container {
    private Integer units;
    private String type;
    private String volume;
}
