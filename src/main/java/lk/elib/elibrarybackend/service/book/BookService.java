package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.views.BookView;

import java.util.List;

public interface BookService {
    List<BookView> getBookList();

    BookDto findById(int id);

    List<BookView> search(String query);

    List<BookView> findByCategory(String category);

    BookDto save(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void deleteById(int id);
}
