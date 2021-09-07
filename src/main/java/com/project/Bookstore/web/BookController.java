package com.project.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class BookController {

	@Controller
	class FriendController {
		@GetMapping("/index")
		public String bookStore(Model model) {

			return "books";
		}

	}
}
