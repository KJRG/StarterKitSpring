package pl.spring.demo.service.impl;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {

	@Autowired
    private BookDao bookDao;

	@Autowired
	private BookMapper mapper;
	
    @Override
    public List<BookTo> findAllBooks() {
    	List<BookEntity> allBookEntities = bookDao.findAll();
    	List<BookTo> allBooks = new ArrayList<>();
    	for(BookEntity be : allBookEntities) {
    		allBooks.add(mapper.convertToBookTo(be));
    	}
    	
        return allBooks;
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	List<BookEntity> bookEntities = bookDao.findBookByTitle(title);
    	List<BookTo> booksFoundByTitle = new ArrayList<>();
    	for(BookEntity be : bookEntities) {
    		booksFoundByTitle.add(mapper.convertToBookTo(be));
    	}
    	
    	return booksFoundByTitle;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	List<BookEntity> bookEntities = bookDao.findBooksByAuthor(author);
    	List<BookTo> booksFoundByAuthor = new ArrayList<>();
    	for(BookEntity be : bookEntities) {
    		booksFoundByAuthor.add(mapper.convertToBookTo(be));
    	}
    	
    	return booksFoundByAuthor;
    }

    @Override
    public BookTo saveBook(BookTo book) {
        BookEntity result = bookDao.save(mapper.convertToBookEntity(book));
        return mapper.convertToBookTo(result);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
