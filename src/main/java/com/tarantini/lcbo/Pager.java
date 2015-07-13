package com.tarantini.lcbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pager {
    @JsonProperty("records_per_page")
    private int recordsPerPage;
    @JsonProperty("total_record_count")
    private int totalRecordCount;
    @JsonProperty("current_page_record_count")
    private int currentPageRecordCount;
    @JsonProperty("is_first_page")
    private boolean isFirstPage;
    @JsonProperty("is_final_page")
    private boolean isFinalPage;
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("current_page_path")
    private String currentPagePath;
    @JsonProperty("next_page")
    private int nextPage;
    @JsonProperty("next_page_path")
    private String nextPagePath;
    @JsonProperty("previous_page")
    private int previousPage;
    @JsonProperty("previous_page_path")
    private String previousPagePath;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_pages_path")
    private String totalPagesPath;
}
