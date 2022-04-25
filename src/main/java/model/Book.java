package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enumeration.Category;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @Enumerated(value = EnumType.STRING)
    Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    Author author;

    Integer availableCopies;

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
