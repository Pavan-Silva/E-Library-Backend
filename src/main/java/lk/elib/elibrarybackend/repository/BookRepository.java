package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.entity.Category;
import lk.elib.elibrarybackend.views.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("FROM Book")
    List<BookView> getBookList();

    List<BookView> findByTitleLike(String query);

    List<BookView> findByCategory(Category category);

    boolean existsById(int id);
}