package com.digiHR.user.utility.response;

import lombok.Data;

@Data
public class PaginatedApiResponse<T>{

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private T data;

    public PaginatedApiResponse(T data, Integer currentPage, Integer totalPages, Long totalElements ) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = data;
    }
}