package pl.spring.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

public class BookSearchRepositoryImpl implements CustomBookSearchRepository {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBookByCriteria(BookSearchCriteria criteria) {
		QBookEntity entity = QBookEntity.bookEntity;
		JPQLQuery query = new JPAQuery(entityManager);
		
		query = query.from(entity);
		
		if(criteria.getTitle() != null) {
//			query = query.where(entity.title.eq(criteria.getTitle()));
			query = query.where(entity.title.toLowerCase().startsWith(criteria.getTitle().toLowerCase()));
		}

//		if(criteria.getAuthor() != null) {
//			query = query.where(entity.authors.any().);
//		}
		
		if(criteria.getLibraryName() != null) {
//			query = query.where(entity.library.name.eq(criteria.getLibraryName()));
			query = query.where(entity.library.name.toLowerCase().startsWith(criteria.getLibraryName().toLowerCase()));
		}

		List<BookEntity> rows = query.list(entity);
		
		return rows;
	}

}
