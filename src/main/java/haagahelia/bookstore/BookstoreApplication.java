package haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.bookstore.domain.Book;
import haagahelia.bookstore.domain.BookRepository;
import haagahelia.bookstore.domain.Category;
import haagahelia.bookstore.domain.CategoryRepository;
import haagahelia.bookstore.domain.User;
import haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Action"));
			// save object of database
			
			brepository.save(new Book("John", "The Moon", "230023-213", 1992, crepository.findByName("Horror").get(0)));
			brepository.save(new Book("Kate", "The Sun", "123124-213", 1973, crepository.findByName("Action").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all students");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
