package service.impl;

import lombok.AllArgsConstructor;
import model.Author;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import service.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
