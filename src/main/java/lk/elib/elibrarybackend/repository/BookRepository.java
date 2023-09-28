package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.projection.BookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b")
    List<BookList> getBookList();

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :query, '%') ")
    List<BookList> searchByName(String query);
}