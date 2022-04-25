package web;

import lombok.AllArgsConstructor;
import model.Book;
import model.BookDto;
import model.exceptions.AuthorIdNotFoundException;
import model.exceptions.BookIdNotFoundException;
import model.exceptions.BookNameExistsException;
import model.exceptions.NoBookCopiesLeftException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping(value = {"/","/all"})
    public List<Book> getBooks(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) throws BookIdNotFoundException {
        return bookService.findBookById(id).map(b -> ResponseEntity.ok().body(b)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pagination")
    public List<Book> getBooksWithPagination(Pageable p){
        return bookService.findAllWithPagination((org.springframework.data.domain.Pageable) p).getContent();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto book) throws AuthorIdNotFoundException, BookNameExistsException {
        return bookService.add(book).map(b -> ResponseEntity.ok().body(b)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto book) throws BookIdNotFoundException, AuthorIdNotFoundException, BookNameExistsException {
        return bookService.update(book, id).map(b -> ResponseEntity.ok().body(b)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}/mark")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable Long id) throws BookIdNotFoundException, NoBookCopiesLeftException {
        return bookService.markAsTaken(id).map(b -> ResponseEntity.ok().body(b)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) throws BookIdNotFoundException {
        return bookService.delete(id).map(b -> ResponseEntity.ok().body(b)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
