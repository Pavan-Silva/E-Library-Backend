package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.projection.BookFilter;

import java.util.List;

public interface BookService {
    List<BookFilter> getBookList();

    BookDto findById(int id);

    List<BookFilter> search(String query);

    BookDto save(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void deleteById(int id);
}
