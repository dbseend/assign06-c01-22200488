package com.ysh.sb_curd_webui.repository;

import com.ysh.sb_curd_webui.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book update(Book book);
    void deleteById(Long id);
}
