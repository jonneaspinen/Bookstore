package com.project.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.Bookstore.domain.Book;
import com.project.Bookstore.domain.BookRepository;
import com.project.Bookstore.domain.Department;
import com.project.Bookstore.domain.DepartmentRepository;
import com.project.Bookstore.domain.User;
import com.project.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, DepartmentRepository drepository,
			UserRepository urepository) {
		return (args) -> {

			log.info("save departments");
			drepository.save(new Department("Fiction"));
			drepository.save(new Department("Magical Realism"));
			drepository.save(new Department("Classics"));

			log.info("save books");
			repository.save(new Book("Haruki Murakami", "Hard-Boiled Wonderland and the End of the World",
					"0-679-74346-4", "1985", "9,90", drepository.findByName("Magical Realism").get(0)));

			repository.save(new Book("Katey Springboots", "Katy goes crazy and is reborn as a computer",
					"0-123-45678-9", "2020", "3,00", drepository.findByName("Fiction").get(0)));

			repository.save(new Book("George Orwell", "1984", "9780451524935", "1949", "9,00",
					drepository.findByName("Classics").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
