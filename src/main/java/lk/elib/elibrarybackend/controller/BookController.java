package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.BookDto;
import lk.elib.elibrarybackend.service.book.BookService;
import lk.elib.elibrarybackend.views.BookView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookView> findAllBooks() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookView> searchBooks(@RequestParam String query) {
        return bookService.search(query);
    }

    @GetMapping("/categories")
    public List<BookView> findBooksByCategory(@RequestParam String category) {
        return bookService.findByCategory(category);
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