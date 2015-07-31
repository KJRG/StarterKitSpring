package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Mock
	private BookMapper mapper;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo book = new BookTo(null, "title", "author author");
		Mockito.when(bookDao.save(Matchers.any(BookEntity.class))).thenAnswer(new Answer<BookEntity>() {
			@Override
			public BookEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if(arguments != null && arguments.length > 0 && arguments[0] != null) {
					return new BookEntity(1L, "title", Arrays.asList(new AuthorTo(0L, "author", "author")));
				}
				
				return null;
			}
		});
		Mockito.when(mapper.convertToBookEntity(Matchers.any(BookTo.class))).thenAnswer(new Answer<BookEntity>() {
			@Override
			public BookEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if(arguments != null && arguments.length > 0 && arguments[0] != null) {
					BookTo bt = (BookTo) arguments[0];
					return new BookEntity(bt.getId(), bt.getTitle(), Arrays.asList(new AuthorTo(0L, "author", "author")));
				}
				
				return null;
			}
		});
		Mockito.when(mapper.convertToBookTo(Matchers.any(BookEntity.class))).thenAnswer(new Answer<BookTo>() {
			@Override
			public BookTo answer(InvocationOnMock invocation) throws Throwable {
				Object arguments[] = invocation.getArguments();
				if(arguments != null && arguments.length > 0 && arguments[0] != null) {
					BookEntity be = (BookEntity) arguments[0];
					return new BookTo(be.getId(), be.getTitle(), "author author");
				}
				
				return null;
			}
		});
		
		// when
		BookTo result = bookService.saveBook(book);
		
		// then
		Mockito.verify(mapper).convertToBookEntity(book);
		Mockito.verify(bookDao).save(Matchers.any(BookEntity.class));
		Mockito.verify(mapper).convertToBookTo(Matchers.any(BookEntity.class));
		assertEquals(1L, result.getId().longValue());
	}
}
