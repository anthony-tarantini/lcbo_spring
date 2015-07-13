package com.tarantini.lcbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LcboResponse<T> {
    private int status;
    private String message;
    private Pager pager;
    private T result;
}
