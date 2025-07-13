package pl.coderslab.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    private Long id;
    private String title;

    public Book() {}

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

}

