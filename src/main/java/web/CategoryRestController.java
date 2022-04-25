package web;

import lombok.AllArgsConstructor;
import model.enumeration.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @GetMapping
    public List<Category> getCategories(){
        return Arrays.stream(Category.values()).sequential().collect(Collectors.toList());
    }
}
