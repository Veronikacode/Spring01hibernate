package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.app.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    Optional<Author> findByPesel(Integer pesel);

    List<Author> findByLastName(String lastName);

    @Query("SELECT a FROM Author a WHERE a.email LIKE :prefix%")
    List<Author> findByEmailStartingWith(@Param("prefix") String prefix);

    @Query("SELECT a FROM Author a WHERE CAST(a.pesel AS string) LIKE :prefix%")
    List<Author> findByPeselStartingWith(@Param("prefix") String prefix);

}
