package haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import haagahelia.bookstore.domain.Book;
import haagahelia.bookstore.domain.BookRepository;
import haagahelia.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	// Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
    
	@RequestMapping("/")
	public String bookList(Model model) {
		model.addAttribute("book", repository.findAll());
		return "booklist";
	}

    @RequestMapping(value = "/add")
    public String addStudent(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categorys", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
    
    @PreAuthorize("hasAuthority('ADMIN')") // only for admin user
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }     
}
