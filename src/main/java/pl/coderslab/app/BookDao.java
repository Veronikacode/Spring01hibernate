package pl.coderslab.app.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public void delete(Book book) {
        if (entityManager.contains(book)) {
            entityManager.remove(book);
        }
    }
}
