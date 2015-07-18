package com.tarantini.lcbo.domain.gateway;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hours {
    private String sunday;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
}
