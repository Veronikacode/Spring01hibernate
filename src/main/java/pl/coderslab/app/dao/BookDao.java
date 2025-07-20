package pl.coderslab.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Publisher;

import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class);
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    public List<Book> findAllWithPublisher() {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher IS NOT NULL", Book.class)
                .getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher", Book.class)
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<Book> findAllByAuthor(Author author) {
        return entityManager.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }
}
