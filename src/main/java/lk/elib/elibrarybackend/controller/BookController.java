package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.Book;
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
    public Book findBookById(@PathVariable int id) {
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
    public Book addNewBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteById(id);
    }
}