package model;

import lombok.Data;
import model.enumeration.Category;

@Data
public class BookDto {
    String name;

    Category category;

    Long authorId;

    Integer availableCopies;
}
