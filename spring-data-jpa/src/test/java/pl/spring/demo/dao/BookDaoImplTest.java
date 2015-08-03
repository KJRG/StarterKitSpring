package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.AuthorTo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "file:src/test/resources/pl/spring/demo/service/CommonServiceTest-context.xml")
@ContextConfiguration(locations = "file:src/main/resources/spring/spring-context.xml")
public class BookDaoImplTest {

	@Autowired
	private BookDao bookDao;
	
	@Test
	public void testShouldSaveBookWithId() {
		// given
        BookEntity book = new BookEntity(null, "title", Arrays.asList(new AuthorTo(0L, "author", "author")));
        // when
        BookEntity result = bookDao.save(book);
        // then
        assertNotNull(result.getId());
       /* the test uses context - the BookServiceImpl object
        * initially has six predefined books added to the collection,
        * the id of the next saved book should be equal to 7L
        */
        assertEquals(7L, result.getId().longValue());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testDaoShouldThrowBookNotNullIdException() {
		// given
		BookEntity book = new BookEntity(1L, "title", Arrays.asList(new AuthorTo(0L, "author", "author")));
		// when
        bookDao.save(book);
		// then
        fail("test should throw BookNotNullIdException");
	}

}
