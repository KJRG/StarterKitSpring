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
		assertEquals(3, allBooks.size());
	}

	@Test
	public void testShouldFindBooksOnlyByTitle() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria("Pierwsza książka", null, null);
		//when
		List<BookTo> allBooks = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(1, allBooks.size());
	}

	@Test
	public void testShouldFindBooksOnlyByLibraryName() {
		//given
		BookSearchCriteria criteria = new BookSearchCriteria(null, null, "Biblioteka Miejska");
		//when
		List<BookTo> allBooks = bookSearchServiceImpl.findBookByCriteria(criteria);
		//then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(3, allBooks.size());
	}

}
