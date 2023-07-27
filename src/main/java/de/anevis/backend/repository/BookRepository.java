package de.anevis.backend.repository;
import de.anevis.backend.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :filterValue, '%'))" +
            " OR lower(b.authorName) LIKE lower(concat('%', :filterValue, '%'))"
    )

    Page<Book> findByFilterValue(String filterValue, Pageable pageable);
}
