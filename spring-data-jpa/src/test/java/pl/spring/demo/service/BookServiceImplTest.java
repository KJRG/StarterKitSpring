package pl.spring.demo.service;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

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
//  @Ignore
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
//  @Ignore
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
//  @Ignore
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
//  @Ignore
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
//  @Ignore
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
//  @Ignore
    public void testShouldFindAllBooksByAuthorsLastNamesPrefix() {
    	// given
    	final String author = "Fredro";
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
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }

    @Test
//  @Ignore
    public void testZShouldSaveBook() {
    	// given
    	BookTo bookToSave1 = new BookTo(null, "title", "author author");
    	BookTo bookToSave2 = new BookTo(null, "title", "author author");
    	BookTo bookToSave3 = new BookTo(null, "title", "author author");
    	
    	// when
    	BookTo result1 = bookService.saveBook(bookToSave1);
    	BookTo result2 = bookService.saveBook(bookToSave2);
    	BookTo result3 = bookService.saveBook(bookToSave3);
    	
    	// then
    	assertNotNull(bookToSave1.getId());
    	assertNotNull(bookToSave2.getId());
    	assertNotNull(bookToSave3.getId());
    	assertNotEquals(result1.getId(), result2.getId());
    	assertNotEquals(result2.getId(), result3.getId());
    	assertNotEquals(result3.getId(), result1.getId());
    }
}
