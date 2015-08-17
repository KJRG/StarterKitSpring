package pl.spring.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.BooleanBuilder;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

public class BookSearchRepositoryImpl implements CustomBookSearchRepository {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBookByCriteria(BookSearchCriteria criteria) {
		QBookEntity entity = QBookEntity.bookEntity;
		BooleanBuilder builder = new BooleanBuilder(),
				doesAuthorMatch = new BooleanBuilder();
		JPQLQuery query = new JPAQuery(entityManager);

		query = query.from(entity);

		if (criteria.getTitle() != null) {
			builder.and(entity.title.startsWithIgnoreCase(criteria.getTitle()));
		}

		if (criteria.getAuthor() != null) {
			String[] authors = criteria.getAuthor().split(",");

			for (String author : authors) {
				String[] fullName = author.split(" ");

				if (fullName.length == 1) {
					doesAuthorMatch.or(entity.authors.any().firstName
							.startsWithIgnoreCase(fullName[0]));
					doesAuthorMatch.or(entity.authors.any().lastName
							.startsWithIgnoreCase(fullName[0]));
					continue;
				}

				if (fullName.length == 2) {
					doesAuthorMatch.or(entity.authors.any().firstName
							.startsWithIgnoreCase(fullName[0]));
					doesAuthorMatch.or(entity.authors.any().lastName
							.startsWithIgnoreCase(fullName[1]));
					continue;
				}
			}

			builder.and(doesAuthorMatch);
		}

		if (criteria.getLibraryName() != null) {
			builder.and(entity.library.name
					.startsWithIgnoreCase(criteria.getLibraryName()));
		}

		return query.where(builder).list(entity);
	}

}
