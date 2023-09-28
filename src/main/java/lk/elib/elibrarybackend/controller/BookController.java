package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.Book;
import lk.elib.elibrarybackend.projection.BookList;
import lk.elib.elibrarybackend.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookList> findAllBooks() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookList> searchBooks(@RequestParam String query) {
        return bookService.search(query);
    }

    @GetMapping("/categories")
    public List<BookList> findBooksByCategory(@RequestParam String query) {
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