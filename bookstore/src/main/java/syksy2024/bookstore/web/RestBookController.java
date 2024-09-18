package syksy2024.bookstore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import syksy2024.bookstore.model.Book;
import syksy2024.bookstore.model.BookRepository;
import syksy2024.bookstore.model.CategoryRepository;

@RestController
public class RestBookController {

    private static final Logger log = LoggerFactory.getLogger(RestBookController.class);
    
    @Autowired
    BookRepository bRepo;
    @Autowired
    CategoryRepository cRepo;

    @GetMapping("/allbooks")
    public Iterable<Book> getAllBooks() {
        log.info("Fetch all books from the database to client as json");
        return bRepo.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getOneBook(@PathVariable("id") Long bookId) {
        log.info("Fetch one book from the database to client as json " + bookId);
        return bRepo.findById(bookId);
    }

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook) {
        log.info("Save a new book" + newBook);
        return bRepo.save(newBook);
    }

    @PutMapping("/book/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        log.info("editBook =" + editedBook);
        log.info("edit book, id = " + id);
        editedBook.setId(id);
        return bRepo.save(editedBook);
    }

    @DeleteMapping("/book/{id}")
    public Iterable<Book> deleteBook(@PathVariable("id") Long bookId) {
        log.info("Delete book " + bookId);
        bRepo.deleteById(bookId);
        return bRepo.findAll();
    }

}
