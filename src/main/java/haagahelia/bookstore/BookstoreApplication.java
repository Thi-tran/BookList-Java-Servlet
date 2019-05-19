package haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import haagahelia.bookstore.domain.Book;
import haagahelia.bookstore.domain.BookRepository;
import haagahelia.bookstore.domain.Category;
import haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Action"));
			// save object of database
			
			brepository.save(new Book("John", "The Moon", "230023-213", 1992, crepository.findByName("Horror").get(0)));
			brepository.save(new Book("Kate", "The Sun", "123124-213", 1973, crepository.findByName("Action").get(0)));

		};
	}
}
