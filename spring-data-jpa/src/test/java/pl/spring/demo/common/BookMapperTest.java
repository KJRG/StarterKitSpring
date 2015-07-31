package pl.spring.demo.common;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper mapper;
	
	@Before
	public void setUp() {
		mapper = new BookMapper();
	}

	@Test
	public void shouldConvertBookEntityToBookTo() {
		// given
		BookEntity bookToConvert = new BookEntity(1L, "title",
				Arrays.asList(new AuthorTo(0L, "author", "author")));
		// when
		BookTo converted = mapper.convertToBookTo(bookToConvert);
		// then
		assertEquals(bookToConvert.getId(), converted.getId());
		assertEquals(bookToConvert.getTitle(), converted.getTitle());
		String[] fullNames = converted.getAuthors().split(",");
		int i = 0;
		for(String fn : fullNames) {
			String[] fullName = fn.split(" ");
			assertEquals(bookToConvert.getAuthors().get(i).getFirstName(), fullName[0]);
			assertEquals(bookToConvert.getAuthors().get(i).getLastName(), fullName[1]);
		}
	}

	@Test
	public void shouldConvertBookToToBookEntity() {
		// given
		BookTo bookToConvert = new BookTo(1L, "title", "author author");
		// when
		BookEntity converted = mapper.convertToBookEntity(bookToConvert);
		// then
		assertEquals(bookToConvert.getId(), converted.getId());
		assertEquals(bookToConvert.getTitle(), converted.getTitle());
		String[] fullNames = bookToConvert.getAuthors().split(",");
		int i = 0;
		for(String fn : fullNames) {
			String[] fullName = fn.split(" ");
			assertEquals(fullName[0], converted.getAuthors().get(i).getFirstName());
			assertEquals(fullName[1], converted.getAuthors().get(i).getLastName());
		}
	}

}
