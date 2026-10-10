package com.webservice.week06.dto;

/** HTML Form의 name 속성(title, author, price, category, isbn)과 필드명이 일치해야 바인딩된다. */
public record BookRequest(
        String title,
        String author,
        int price,
        String category,
        String isbn) {
}
