package pl.coderslab.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Publisher;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void delete(Long id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        if (publisher != null) {
            entityManager.remove(publisher);
        }
    }
}
