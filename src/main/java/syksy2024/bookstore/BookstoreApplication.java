package syksy2024.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import syksy2024.bookstore.model.Book;
import syksy2024.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

		@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "978-0-7475-3269-9", 24.90));
			repository.save(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, "0-7475-3849-2", 24.90));
			repository.save(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, "0-7475-4215-5", 22.90));
			repository.save(new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", 2000, "0-7475-5079-4", 22.90));
			repository.save(new Book("Harry Potter and the Order of the Phoenix", "J.K. Rowling", 2003, "0-7475-5100-6", 19.90));
			repository.save(new Book("Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2005, "0-7475-8108-8", 19.90));
			repository.save(new Book("Harry Potter and the Deathly Hallows", "J.K. Rowling", 2007, "0-7475-9105-9", 14.90));


			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
