package com.ysh.sb_curd_webui.service;

import com.ysh.sb_curd_webui.domain.Book;
import com.ysh.sb_curd_webui.dto.BookRequest;
import com.ysh.sb_curd_webui.dto.BookResponse;
import com.ysh.sb_curd_webui.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponse create(BookRequest r) {
        Book saved = repository.save(new Book(null, r.title(), r.author(), r.price(), r.category(), r.isbn()));
        return toResponse(saved);
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public BookResponse findById(Long id) {
        return toResponse(findBook(id));
    }

    public BookResponse update(Long id, BookRequest r) {
        Book b = findBook(id);
        b.setTitle(r.title());
        b.setAuthor(r.author());
        b.setPrice(r.price());
        b.setCategory(r.category());
        b.setIsbn(r.isbn());
        return toResponse(repository.update(b));
    }

    public void delete(Long id) {
        findBook(id);
        repository.deleteById(id);
    }

    private Book findBook(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + id));
    }

    private BookResponse toResponse(Book b) {
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getPrice(), b.getCategory(), b.getIsbn());
    }
}
