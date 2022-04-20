package com.dannielcervantes.bookclub.controllers;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.dannielcervantes.bookclub.models.Book;
import com.dannielcervantes.bookclub.services.MainService;

@Controller
public class BooksController {
	@Autowired
	private MainService mainService;
	//=====Renders dashboard page
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) { // Checks to see if in session
			return "redirect:/";
		}
		model.addAttribute("books", mainService.allBooks());
		return "dashboard.jsp";
	}
	
	//==================Create new Book form
	@GetMapping("/books/new")
	public String newBookForm(@ModelAttribute("book")Book book, HttpSession session) {
		if(session.getAttribute("userId") == null) { // Checks to see if in session
			return "redirect:/";
		}
		return "newBook.jsp";
	}
	
	//==================Process Book form
	@PostMapping("/books/new")
	public String processCreateBook(@Valid @ModelAttribute("book")Book book,
			BindingResult result) {
		if(result.hasErrors()) {
			return "newBook.jsp";
		} else {
			mainService.createBook(book);
			return "redirect:/dashboard";
		}
	}
	
	// ============Render edit book
	@GetMapping("/books/{id}/edit")
	public String updateBook(Model model, @PathVariable("id")Long id, HttpSession session) {
		if(session.getAttribute("userId") != mainService.oneBook(id).getReader().getId()) { // Checks to see if in session
			return "redirect:/dashboard";
		}
		model.addAttribute("book", mainService.oneBook(id));
		return "editBook.jsp";
	}
	
	//==========process edit book
	@PutMapping("/books/{id}/edit")
	public String processUpdateBook(@Valid @ModelAttribute("book")Book book,
			BindingResult result) {
		if(result.hasErrors()) {
			return"editBook.jsp";
		} else {
			mainService.updateBook(book);
			return "redirect:/dashboard";
		}
	}
	//===========Render show page
	@GetMapping("/books/{id}/show")
	public String showBook(@PathVariable("id")Long id, Model model, HttpSession session) {
		model.addAttribute("book", mainService.oneBook(id));
		model.addAttribute("user_id", (Long)session.getAttribute("userId"));
		
		return "showBook.jsp";	
	}
	
	//============Delete book
	@DeleteMapping("/books/{id}/delete")
	public String deleteBook(@PathVariable("id")Long id) {
		mainService.deleteBook(id);
		
		return "redirect:/dashboard";
	}
}
