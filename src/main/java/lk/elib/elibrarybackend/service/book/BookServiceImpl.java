package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.projection.BookList;
import lk.elib.elibrarybackend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

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
            throw new ResourceNotFoundException("Invalid book id - " + id);
        }
    }

    @Override
    public List<BookList> search(String query) {
        return bookRepository.search(query);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        Optional<Book> bookOpt = bookRepository.findById(book.getId());

        if (bookOpt.isPresent()) {
            return bookRepository.save(book);

        } else {
            throw new ResourceNotFoundException("Invalid book id - " + book.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()) {
            bookRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid book id - " + id);
        }
    }
}
