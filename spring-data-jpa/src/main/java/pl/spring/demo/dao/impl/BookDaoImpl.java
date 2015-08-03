package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		 return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> booksFoundByTitle = new ArrayList<>();
		for (BookEntity be : ALL_BOOKS) {
			if (be.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
				booksFoundByTitle.add(be);
			}
		}
		return booksFoundByTitle;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> booksFoundByAuthor = new ArrayList<>();
		for(BookEntity be : ALL_BOOKS) {
			for(AuthorTo a : be.getAuthors()) {
				if(a.getFirstName().toLowerCase().startsWith(author.toLowerCase())
						|| a.getLastName().toLowerCase().startsWith(author.toLowerCase())) {
					booksFoundByAuthor.add(be);
					continue;
				}
			}
		}
		return booksFoundByAuthor;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia",
				Arrays.asList(new AuthorTo(0L, "Wiliam", "Szekspir"))));

		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole",
				Arrays.asList(new AuthorTo(0L,
						"Hanna", "Ożogowska"))));

		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza",
				Arrays.asList(new AuthorTo(0L,
						"Jan", "Parandowski"))));

		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju",
				Arrays.asList(new AuthorTo(0L,
						"Edmund", "Niziurski"))));

		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				Arrays.asList(new AuthorTo(0L,
						"Zbigniew", "Nienacki"))));

		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorTo(0L,
						"Aleksander", "Fredro"))));
	}
}
