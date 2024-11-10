package com.vvecon.product_service.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageDTO<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int currentPage;
    private boolean first;
    private boolean last;
    private boolean empty;
    private Sort sort;

    public static <T> PageDTO<T> fromPage(Page<T> page) {
        return PageDTO.<T>builder()
                .content(page.getContent())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .sort(page.getSort())
                .build();
    }
}

