package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByCategory_Id(Long categoryId);

    List<Book> findByAuthorsContains(Author author);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRating(int rating);

    Optional<Book> findFirstByCategoryOrderByTitle(Category category);

}
