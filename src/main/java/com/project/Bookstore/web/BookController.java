package com.project.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Bookstore.domain.Book;
import com.project.Bookstore.domain.BookRepository;
import com.project.Bookstore.domain.DepartmentRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	@Autowired
	private DepartmentRepository drepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

	// LIST ALL
	@RequestMapping(value = "/booklist")
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	// RESTful LIST
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> listBooksRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful FIND
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

	// ADD
	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("departments", drepository.findAll());
		return "addbook";
	}

	// SAVE
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// DELETE
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	// EDIT
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("departments", drepository.findAll());

		return "editbook";
	}
}
