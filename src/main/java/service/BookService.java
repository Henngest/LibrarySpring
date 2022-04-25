package service;

import model.Book;
import model.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findBookById(Long id);
    Page<Book> findAllWithPagination(Pageable p);
    Optional<Book> add(BookDto book);
    Optional<Book> update(BookDto book, Long id);
    Optional<Book> delete(Long id);
    Optional<Book> markAsTaken(Long id);
}
