package ru.lalibrairiestore.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO<T> {
    /**
     * Quantity of elements
     */
    private Long totalElements;

    /**
     * Quantity of pages
     */
    private int totalPages;

    /**
     * Page number
     */
    private int number;

    /**
     * Page size
     */
    private int size;

    /**
     * Content list (list of products)
     */
    private List<T> content;

    public PageDTO(Page<T> page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.number = page.getNumber();
        this.size = page.getSize();
        this.content = page.getContent();
    }
}
