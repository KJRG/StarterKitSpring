package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.service.BookSearchService;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookSearchServiceTest {

	@Autowired
	private BookSearchService bookSearchServiceImpl;
	
	@Test
	public void testShouldFindAllBooks() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, null, null);
		//when
		List<BookTo> allBooks = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(8, allBooks.size());
	}

	@Test
	public void testShouldFindBooksOnlyByTitle() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria("Pierwsza książka", null, null);
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindBooksOnlyByAuthorsFirstName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, "jan", null);
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(2, books.size());
	}

	@Test
	public void testShouldFindBooksOnlyByAuthorsLastName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, "now", null);
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindBooksOnlyByAuthorsFullName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, "Zbigniew Nowak", null);
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindBooksOnlyByLibraryName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, null, "biblioteka miejska");
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(4, books.size());
	}

	@Test
	public void testShouldFindBooksByTitleAndAuthorsName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria("Hobbit", "Tolkien", null);
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(3, books.size());
	}

	@Test
	public void testShouldFindBooksByTitleAndLibraryName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria("Hobbit", null, "Biblioteka Miejska");
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}
	
	@Test
	public void testShouldFindBooksByAuthorsNameAndLibraryName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, "Stephen King", "Biblioteka Wrocławska");
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindBooksByAllCriteria() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria("Hobbit", "Tolkien", "Biblioteka Państwowa");
		//when
		List<BookTo> books = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}
}
