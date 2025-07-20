package pl.coderslab.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Person;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Person person) {
        entityManager.persist(person);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void delete(Long id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}
