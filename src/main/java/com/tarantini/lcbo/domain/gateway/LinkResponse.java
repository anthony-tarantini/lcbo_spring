package com.tarantini.lcbo.domain.gateway;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class LinkResponse {
    private List<Link> links = new ArrayList<>();

    public void addLink(final Link link) {
        links.add(link);
    }
}
