package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.projection.BookFilter;

import java.util.List;

public interface BookService {
    List<BookFilter> getBookList();

    Book findById(int id);

    List<BookFilter> search(String query);

    Book save(Book book);

    Book update(Book book);

    void deleteById(int id);
}
