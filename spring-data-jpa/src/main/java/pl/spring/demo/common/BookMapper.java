package pl.spring.demo.common;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.BookEntity;

@Component
public class BookMapper {
	public BookTo convertToBookTo(BookEntity bookToConvert) {
		BookTo converted = new BookTo();
		converted.setId(bookToConvert.getId());
		converted.setTitle(bookToConvert.getTitle());
		
		StringBuilder authors = new StringBuilder();
		ListIterator<AuthorTo> iter = bookToConvert.getAuthors().listIterator();
		
		while(iter.hasNext()) {
			AuthorTo a = iter.next();
			authors.append(a.getFirstName() + " " + a.getLastName());
			if(iter.hasNext()) {
				authors.append(",");
			}
		}
		converted.setAuthors(authors.toString());
		
		return converted;
	}

	public BookEntity convertToBookEntity(BookTo bookToConvert) {
		BookEntity converted = new BookEntity();
		converted.setId(bookToConvert.getId());
		converted.setTitle(bookToConvert.getTitle());
		
		List<AuthorTo> authorsList = new ArrayList<>();
		String[] authors = bookToConvert.getAuthors().split(",");
		
		for(String a : authors) {
			String[] fullName = a.split(" ");
			authorsList.add(new AuthorTo(0L, fullName[0], fullName[1]));
		}
		converted.setAuthors(authorsList);
		
		return converted;
	}
}
