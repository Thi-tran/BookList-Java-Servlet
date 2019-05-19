package haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.bookstore.domain.Book;
import haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {

			// save object of database
			repository.save(new Book("Ernest Hemingway", "A FareWell to", "1231241-23", 1929));
			repository.save(new Book("George Orwell", "Animal Farm", "123-23", 1945));

		};
	}

}
