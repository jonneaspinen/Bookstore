package com.project.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.Bookstore.domain.Book;
import com.project.Bookstore.domain.BookRepository;
import com.project.Bookstore.domain.DepartmentRepository;
import com.project.Bookstore.web.BookController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	@Autowired
	private DepartmentRepository drepository;
	
	@Autowired
	private BookController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void findByIsbnShouldReturnAuthor() {
		List<Book> books = repository.findByIsbn("0-679-74346-4");
		// assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Haruki Murakami");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Frank Herbert", "Dune", "0340839937", "2021", "16,30", drepository.findByName("Classics").get(0));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

}