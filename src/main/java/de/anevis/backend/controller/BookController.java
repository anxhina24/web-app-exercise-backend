package de.anevis.backend.controller;

import de.anevis.backend.domain.Book;
import de.anevis.backend.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String filterValue
    ) {
        return bookService.findAll(pageNo, pageSize, filterValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book saveBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book updateBook = bookService.update(id, book);
        if(updateBook != null){
            return ResponseEntity.ok(updateBook);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing Entity");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        boolean deletedBook = bookService.delete(id);
        if(deletedBook){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
