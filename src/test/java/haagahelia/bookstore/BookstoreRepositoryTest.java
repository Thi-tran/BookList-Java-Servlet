package haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import haagahelia.bookstore.domain.Book;
import haagahelia.bookstore.domain.BookRepository;
import haagahelia.bookstore.domain.Category;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BookstoreRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("The Moon");
	
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor().equals("John"));
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("John", "The Moon", "230023-213", 1992, new Category("BITE"));
		repository.save(book);
    	assertThat(book.getId()).isNotNull();
	}
	
	@Test 
	public void deleteBook() {
		Book book = new Book("John", "The Moon", "230023-213", 1992, new Category("BITE"));
		repository.save(book);
		Long bookID = book.getId();
		repository.deleteById(bookID);
		System.out.print("f deleted " + book.getId());
	}
}