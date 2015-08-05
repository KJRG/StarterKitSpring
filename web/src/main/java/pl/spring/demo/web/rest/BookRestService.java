package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
 @ResponseBody
public class BookRestService {

	@Autowired
	private BookService bookService;

//	@ResponseBody
	@RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
	public List<BookTo> findBooksByTitle(
			@RequestParam("titlePrefix") String titlePrefix) {
		return bookService.findBooksByTitle(titlePrefix);
	}

//	@ResponseBody
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public BookTo saveBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(value = "/delete-book/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		bookService.deleteBook(id);
		try {
			response.sendRedirect("/workshop/books");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/books";
	}
}
