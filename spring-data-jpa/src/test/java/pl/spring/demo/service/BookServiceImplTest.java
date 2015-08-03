package pl.spring.demo.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }
    
    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
        assertEquals(1, booksByTitle.size());
    }

    @Test
    public void testShouldFindAllBooksByTitlesPrefix() {
    	// given
    	final String title = "Pan S";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    	assertEquals(1, booksByTitle.size());
    }

    @Test
    public void testShouldFindAllBooksByAuthorsFullFirstName() {
    	// given
    	final String author = "Jan";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(1, booksByAuthor.size());
    }

    @Test
    public void testShouldFindAllBooksByAuthorsFirstNamesPrefix() {
    	// given
    	final String author = "Niz";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(1, booksByAuthor.size());
    }

    @Test
    public void testShouldFindAllBooksByAuthorsFullLastName() {
    	// given
    	final String author = "Fredro";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(1, booksByAuthor.size());
    }

    @Test
    public void testShouldFindAllBooksByAuthorsLastNamesPrefix() {
    	// given
    	final String author = "Fr";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(1, booksByAuthor.size());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
    	// given
    	final BookTo bookToSave = new BookTo(22L, "title", "author author");
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }

    @Test
    public void testZShouldSaveBooks() {
    	// given
    	List<BookTo> booksToSave = new ArrayList<>();
    	Set<Long> booksIds = new HashSet<>();
    	for(int i = 0; i < 3; i++) {
    		booksToSave.add(new BookTo(null, "title", "author author"));
    	}
    	// when
    	for(BookTo bt : booksToSave) {
    		bookService.saveBook(bt);
    	}
    	// then
    	for(BookTo bt : booksToSave) {
    		assertNotNull(bt);
    		booksIds.add(bt.getId());
    	}
    }
}
