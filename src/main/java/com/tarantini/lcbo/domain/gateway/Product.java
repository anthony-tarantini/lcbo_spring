package com.tarantini.lcbo.domain.gateway;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Integer id;
    private String name;
    private String price;
    private String category;
    private String origin;
    private String alcoholContent;
    private String producer;
    private Container container;
    private Image image;
}
