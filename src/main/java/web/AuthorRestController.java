package web;

import lombok.AllArgsConstructor;
import model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.findAll();
    }
}
