package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.projection.BookList;
import lk.elib.elibrarybackend.repository.BookRepository;
import lk.elib.elibrarybackend.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookList> getBookList() {
        return bookRepository.getBookList();
    }

    @Override
    public Book findById(int id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()) {
            return bookOpt.get();

        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<BookList> searchByName(String query) {
        return bookRepository.searchByName(query);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
