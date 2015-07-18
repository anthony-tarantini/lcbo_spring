package com.tarantini.lcbo.domain.lcbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LcboResponse<T> {
    private int status;
    private String message;
    private LcboPager lcboPager;
    private T result;
}
