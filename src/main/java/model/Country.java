package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
