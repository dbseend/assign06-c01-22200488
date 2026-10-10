package com.ysh.sb_curd_webui.controller;

import com.ysh.sb_curd_webui.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ysh.sb_curd_webui.dto.BookRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookWebController {

    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET / : 루트 접근 시 목록으로 이동
    @GetMapping("/")
    public String home() {
        return "redirect:/books";
    }

    // GET /books : 전체 목록 -> Model("books") -> templates/books/list.html
    @GetMapping({"/books", "/books/"})
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // GET /books/new : 등록 Form 화면
    @GetMapping({"/books/new", "/books/new/"})
    public String form() {
        return "books/form";
    }

    // POST /books : Form 데이터(name=title,author,price,category,isbn)를 BookRequest로 바인딩
    @PostMapping({"/books", "/books/"})
    public String create(@ModelAttribute BookRequest request, RedirectAttributes redirectAttributes) {
        bookService.create(request);
        // redirect 이후 1회만 보이는 flash 메시지 (PRG 유지)
        redirectAttributes.addFlashAttribute("message", "'" + request.title() + "' 도서가 등록되었습니다.");
        return "redirect:/books"; // PRG: Post -> Redirect -> Get (새로고침 시 중복 등록 방지)
    }
}
