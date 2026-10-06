package com.ysh.sb_curd_webui.controller;

import com.ysh.sb_curd_webui.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ysh.sb_curd_webui.dto.BookRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookWebController {

    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /books : 전체 목록 -> Model("books") -> templates/books/list.html
    @GetMapping("/books")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // GET /books/new : 등록 Form 화면
    @GetMapping("/books/new")
    public String form() {
        return "books/form";
    }

    // POST /books : Form 데이터(name=title,author,price,category,isbn)를 BookRequest로 바인딩
    @PostMapping("/books")
    public String create(@ModelAttribute BookRequest request, Model model) {
        bookService.create(request);
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }
}
