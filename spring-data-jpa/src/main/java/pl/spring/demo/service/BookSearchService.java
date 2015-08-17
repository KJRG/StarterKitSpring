package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

public interface BookSearchService {
	List<BookTo> findBookByCriteria(BookSearchCriteria criteria);
}
