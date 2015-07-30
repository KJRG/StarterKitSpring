package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.spring.demo.common.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	@Autowired
	private BookMapper mapper;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookTo> findAll() {
		List<BookTo> allBooks = new ArrayList<>();
		for (BookEntity be : ALL_BOOKS) {
			allBooks.add(mapper.convertFromBookEntity(be));
		}
		return allBooks;
		// return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookTo> findBookByTitle(String title) {
		List<BookTo> booksFoundByTitle = new ArrayList<>();
		for (BookEntity be : ALL_BOOKS) {
			if (be.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
				booksFoundByTitle.add(mapper.convertFromBookEntity(be));
			}
		}
		return booksFoundByTitle;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		List<BookTo> booksFoundByAuthor = new ArrayList<>();
		for(BookEntity be : ALL_BOOKS) {
			for(AuthorTo a : be.getAuthors()) {
				if(a.getFirstName().toLowerCase().startsWith(author.toLowerCase())
						|| a.getLastName().toLowerCase().startsWith(author.toLowerCase())) {
					booksFoundByAuthor.add(mapper.convertFromBookEntity(be));
				}
			}
		}
		return booksFoundByAuthor;
	}

	@Override
	@NullableId
	public BookTo save(BookTo book) {
		// ALL_BOOKS.add(book);
		ALL_BOOKS.add(mapper.convertFromBookTo(book));
		return book;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	private void addTestBooks() {
		// ALL_BOOKS.add(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir"));
		// ALL_BOOKS.add(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska"));
		// ALL_BOOKS.add(new BookTo(3L, "Przygody Odyseusza", "Jan
		// Parandowski"));
		// ALL_BOOKS.add(new BookTo(4L, "Awantura w Niekłaju", "Edmund
		// Niziurski"));
		// ALL_BOOKS.add(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew
		// Nienacki"));
		// ALL_BOOKS.add(new BookTo(6L, "Zemsta", "Aleksander Fredro"));

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
