package pl.coderslab.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(PersonDetailsDao personDetailsDao) {
        entityManager.persist(personDetailsDao);
    }

    public void update(PersonDetailsDao personDetailsDao) {
        entityManager.merge(personDetailsDao);
    }

    public PersonDetailsDao findById(Long id) {
        return entityManager.find(PersonDetailsDao.class, id);
    }

    public void delete(Long id) {
        PersonDetailsDao personDetailsDao = entityManager.find(PersonDetailsDao.class, id);
        if (personDetailsDao != null) {
            entityManager.remove(personDetailsDao);
        }
    }
}
