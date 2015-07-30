package pl.spring.demo.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.aop.BookDaoAdvisor;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

public class BookDaoImplTest {

	@Autowired
	private BookDaoImpl bookDao;
	
	private BookDaoAdvisor advisor;
	
	@Test
	public void testShouldSaveBookWithId() {
		// given
        BookTo book = new BookTo(null, "title", "author");
//        bookDao = new BookDaoImpl();
//        advisor = new BookDaoAdvisor();
        // when
//        BookTo result = bookDao.save(book);
//        bookDao.save(book);
        // then
//        assertNotNull(result.getId());
	}

}
