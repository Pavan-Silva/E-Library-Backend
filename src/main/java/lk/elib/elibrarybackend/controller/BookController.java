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
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookList>> searchBooks(@RequestParam(required = false) String query) {

        if (query == null) {
            return ResponseEntity.ok(bookService.getBookList());

        } else {
            return ResponseEntity.ok(bookService.searchByName(query));
        }
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteById(id);
    }
}