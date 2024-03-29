package lk.elib.elibrarybackend.service.book;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.views.BookView;
import lk.elib.elibrarybackend.repository.BookRepository;
import lk.elib.elibrarybackend.repository.CategoryRepository;
import lk.elib.elibrarybackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<BookView> getBookList() {
        return bookRepository.getBookList();
    }

    @Override
    public BookDto findById(int id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()) {
            return Mapper.bookToDto(bookOpt.get());

        } else {
            throw new ResourceNotFoundException("Invalid book id - " + id);
        }
    }

    @Override
    public List<BookView> search(String query) {
        return bookRepository.findByTitleLike(query);
    }

    @Override
    public List<BookView> findByCategory(String category) {
        return bookRepository.findByCategory(categoryRepository.findByNameIgnoreCase(category));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = Mapper.dtoToBook(bookDto);
        return Mapper.bookToDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getId())) {
            Book book = Mapper.dtoToBook(bookDto);
            return Mapper.bookToDto(bookRepository.save(book));

        } else {
            throw new ResourceNotFoundException("Invalid book id - " + bookDto.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid book id - " + id);
        }
    }
}
