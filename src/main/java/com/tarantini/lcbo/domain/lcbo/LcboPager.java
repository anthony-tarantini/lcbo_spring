package com.tarantini.lcbo.domain.lcbo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LcboPager {
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
    private Integer currentPage;
    @JsonProperty("current_page_path")
    private String currentPagePath;
    @JsonProperty("next_page")
    private Integer nextPage;
    @JsonProperty("next_page_path")
    private String nextPagePath;
    @JsonProperty("previous_page")
    private Integer previousPage;
    @JsonProperty("previous_page_path")
    private String previousPagePath;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_pages_path")
    private String totalPagesPath;
}
