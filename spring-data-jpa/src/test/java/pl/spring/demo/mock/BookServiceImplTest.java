package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService = new BookServiceImpl();
	@Mock
	private BookDao bookDao;
	private BookMapper mapper;

	@Before
	public void setUpt() {
		mapper = new BookMapper();
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookService, "mapper", mapper);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo book = new BookTo(null, "title", "author author");
		Mockito.when(bookDao.save(Mockito.any(BookEntity.class))).thenAnswer(new Answer<BookEntity>() {
			@Override
			public BookEntity answer(InvocationOnMock invocation) throws Throwable {
				BookEntity param = (BookEntity) invocation.getArguments()[0];
				param.setId(17L);
				return param;
			}
		});
		
		// when
		BookTo result = bookService.saveBook(book);
		
		// then
		ArgumentCaptor<BookEntity> capture = ArgumentCaptor.forClass(BookEntity.class);
		Mockito.verify(bookDao).save(capture.capture());
		assertEquals(17L, result.getId().longValue());
		assertNotNull(capture.getValue().getId());
		assertEquals(17L, capture.getValue().getId().longValue());
	}
}
