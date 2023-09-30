package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.projection.BookFilter;
import lk.elib.elibrarybackend.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookFilter> findAllBooks() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookFilter> searchBooks(@RequestParam String query) {
        return bookService.search(query);
    }

    @GetMapping("/categories")
    public List<BookFilter> findBooksByCategory(@RequestParam String query) {
        return null;
    }

    @PostMapping
    public BookDto addNewBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteById(id);
    }
}