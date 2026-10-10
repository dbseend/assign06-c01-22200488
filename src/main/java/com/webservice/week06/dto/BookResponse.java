package com.webservice.week06.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        int price,
        String category,
        String isbn) {
}
