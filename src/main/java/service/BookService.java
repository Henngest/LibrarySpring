package service;

import model.Book;
import model.BookDto;
import model.exceptions.AuthorIdNotFoundException;
import model.exceptions.BookIdNotFoundException;
import model.exceptions.BookNameExistsException;
import model.exceptions.NoBookCopiesLeftException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findBookById(Long id) throws BookIdNotFoundException;
    Page<Book> findAllWithPagination(Pageable p);
    Optional<Book> add(BookDto book) throws AuthorIdNotFoundException, BookNameExistsException;
    Optional<Book> update(BookDto book, Long id) throws BookIdNotFoundException, BookNameExistsException, AuthorIdNotFoundException;
    Optional<Book> delete(Long id) throws BookIdNotFoundException;
    Optional<Book> markAsTaken(Long id) throws BookIdNotFoundException, NoBookCopiesLeftException;
}
