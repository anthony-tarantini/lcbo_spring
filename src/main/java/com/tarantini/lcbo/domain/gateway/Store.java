package com.tarantini.lcbo.domain.gateway;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Store {
    private int storeId;
    private String name;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String postalCode;
    private String telephone;
    private float latitude;
    private float longitude;
    private Hours hours;
}