package com.tarantini.lcbo.domain.gateway;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private String full;
    private String thumb;
}
