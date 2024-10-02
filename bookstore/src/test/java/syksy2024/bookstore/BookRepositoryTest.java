package syksy2024.bookstore;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import syksy2024.bookstore.model.Book;
import syksy2024.bookstore.model.BookRepository;
import syksy2024.bookstore.model.Category;
import syksy2024.bookstore.model.CategoryRepository;


@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bRepo;

    @Autowired
    private CategoryRepository cRepo;

    @Test
    public void findByAuthorReturnBook() {
        List<Book> books = bRepo.findByAuthor("Ida Kabris");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Idan testikirja");
        
    }

    @Test
    public void createNewBook() {
        Category category = new Category("Testikategoria");
        cRepo.save(category);
        Book book = new Book("Uusi testikirja", "Uusi testaaja", 2024, "978-0-9999-9999-9", 29.90, category);
        bRepo.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = bRepo.findByTitle("Idan testikirja");
        Book book = books.get(0);
        bRepo.delete(book);
        List<Book> newBooks = bRepo.findByTitle("Idan testikirja");
        assertThat(newBooks).hasSize(0);
    }

    @Test
    public void updateBook() {
        List<Book> books = bRepo.findByTitle("Idan testikirja");
        assertThat(books).hasSize(1);
        Book book = books.get(0);
        book.setTitle("Idan testikirja osa 2");
        bRepo.save(book);
        List<Book> newBooks = bRepo.findByTitle("Idan testikirja osa 2");
        assertThat(newBooks).hasSize(1);
    }
    

}
