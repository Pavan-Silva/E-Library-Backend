package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.entity.Category;
import lk.elib.elibrarybackend.projection.BookFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b")
    List<BookFilter> getBookList();

    List<BookFilter> findByTitleLike(String query);

    List<BookFilter> findByCategory(Category category);

    boolean existsById(int id);
}