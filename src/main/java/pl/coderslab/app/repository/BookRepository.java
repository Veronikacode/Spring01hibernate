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

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.category.name = ?1")
    List<Book> findByCategory(String categoryName);

    List<Book> findByCategory_Id(Long categoryId);

    List<Book> findByAuthorsContains(Author author);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRating(int rating);

    Optional<Book> findFirstByCategoryOrderByTitle(Category category);

    @Query("SELECT b FROM Book b WHERE b.rating BETWEEN :minRating AND :maxRating")
    List<Book> findBooksByRatingBetween(@Param("minRating") int minRating, @Param("maxRating") int maxRating);

    @Query("SELECT b FROM Book b WHERE b.publisher = :publisher")
    List<Book> findBooksByPublisher(@Param("publisher") Publisher publisher);

    @Query("SELECT b FROM Book b WHERE b.category = :category ORDER BY b.title ASC")
    Optional<Book> findFirstBookByCategoryOrderByTitle(@Param("category") Category category);
}


