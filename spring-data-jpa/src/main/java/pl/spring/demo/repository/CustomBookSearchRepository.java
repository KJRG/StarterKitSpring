package pl.spring.demo.repository;

import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.criteria.BookSearchCriteria;

public interface CustomBookSearchRepository {
	List<BookEntity> findBookByCriteria(BookSearchCriteria criteria);
}
