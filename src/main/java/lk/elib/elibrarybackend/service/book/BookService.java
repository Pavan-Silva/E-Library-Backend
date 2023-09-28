package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.projection.BookList;

import java.util.List;

public interface BookService {
    List<BookList> getBookList();

    Book findById(int id);

    List<BookList> search(String query);

    Book save(Book book);

    Book update(Book book);

    void deleteById(int id);
}
