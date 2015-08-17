package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pl.spring.demo.entity.BookEntity;

public interface BookSearchRepository
		extends
			JpaRepository<BookEntity, Long>,
			CustomBookSearchRepository,
			QueryDslPredicateExecutor<BookEntity> {

}
