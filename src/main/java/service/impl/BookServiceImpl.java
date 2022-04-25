package service.impl;

import lombok.AllArgsConstructor;
import model.Author;
import model.Book;
import model.BookDto;
import model.enumeration.Category;
import model.exceptions.AuthorIdNotFoundException;
import model.exceptions.BookIdNotFoundException;
import model.exceptions.BookNameExistsException;
import model.exceptions.NoBookCopiesLeftException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.BookRepository;
import service.BookService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long id) throws BookIdNotFoundException {
        return Optional.ofNullable(bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new));
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable p) {
        return bookRepository.findAll(p);
    }

    @Override
    @Transactional
    public Optional<Book> add(BookDto book) throws AuthorIdNotFoundException, BookNameExistsException {
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(AuthorIdNotFoundException::new);
        Category category = Category.valueOf(book.getCategory().toString());
        if(bookRepository.existsByName(book.getName()))
            throw new BookNameExistsException();
        Book newBook = new Book(book.getName(),category,author,book.getAvailableCopies());
        bookRepository.save(newBook);
        return Optional.of(newBook);
    }

    @Override
    @Transactional
    public Optional<Book> update(BookDto book, Long id) throws BookIdNotFoundException, BookNameExistsException, AuthorIdNotFoundException {
        Book bookToUpdate = bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(AuthorIdNotFoundException::new);
        Category category = Category.valueOf(book.getCategory().toString());
        if(bookRepository.existsByName(book.getName()))
            throw new BookNameExistsException();
        bookToUpdate.setName(book.getName());
        bookToUpdate.setAuthor(author);
        bookToUpdate.setCategory(category);
        bookToUpdate.setAvailableCopies(book.getAvailableCopies());
        bookRepository.save(bookToUpdate);
        return Optional.of(bookToUpdate);
    }

    @Override
    @Transactional
    public Optional<Book> delete(Long id) throws BookIdNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> markAsTaken(Long id) throws BookIdNotFoundException, NoBookCopiesLeftException {
        Book book = bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);
        if(book.getAvailableCopies()<=0)
            throw new NoBookCopiesLeftException();
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
