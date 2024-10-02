package syksy2024.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import syksy2024.bookstore.model.AppUser;
import syksy2024.bookstore.model.AppUserRepository;
import syksy2024.bookstore.model.Book;
import syksy2024.bookstore.model.BookRepository;
import syksy2024.bookstore.model.Category;
import syksy2024.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

		@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository cRepository, AppUserRepository AUrepo) {
		return (args) -> {
			log.info("save some categories");
			cRepository.save(new Category("Fantasy"));
			cRepository.save(new Category("Thriller"));
			cRepository.save(new Category("Romance"));
			cRepository.save(new Category("Horror"));
			log.info("show a couple of categories"); {
				for (Category category : cRepository.findAll()) {
                    log.info(category.toString());
                }
			}


			log.info("save a couple of books");
			brepository.save(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "978-0-7475-3269-9", 24.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, "0-7475-3849-2", 24.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, "0-7475-4215-5", 22.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", 2000, "0-7475-5079-4", 22.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Order of the Phoenix", "J.K. Rowling", 2003, "0-7475-5100-6", 19.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2005, "0-7475-8108-8", 19.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Deathly Hallows", "J.K. Rowling", 2007, "0-7475-9105-9", 14.90, cRepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Idan testikirja", "Ida Kabris", 2024, "0-7475-9105-9", 14.90, cRepository.findByName("Horror").get(0)));


			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

			AppUser user1 = new AppUser("user", "$2y$10$lwe.tT04qaA23g7wpPABYOzBhnMzruF3GcNStWQNIHqK9axUyR312", "USER");
			AppUser user2 = new AppUser("admin", "$2y$10$X3r1h7F0GE.P6FmzJddkvOZe0ADpnrF9fDARqWrKGJaaqpTAMxPfS", "ADMIN");
			AUrepo.save(user1);
			AUrepo.save(user2);
			
		};
	}

}
