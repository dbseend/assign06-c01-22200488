package com.ysh.sb_curd_webui.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        int price,
        String category,
        String isbn) {
}
