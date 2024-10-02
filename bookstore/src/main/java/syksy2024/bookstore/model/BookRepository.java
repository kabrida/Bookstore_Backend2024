package syksy2024.bookstore.model;
import org.springframework.data.repository.CrudRepository;
import java.util.List;





public interface BookRepository extends CrudRepository<Book, Long>{

    public Book findByCategoryId(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
