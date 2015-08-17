package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookSearchRepository;
import pl.spring.demo.service.BookSearchService;
import pl.spring.demo.to.BookTo;

@Service
@Transactional(readOnly = true)
public class BookSearchServiceImpl implements BookSearchService {

	@Autowired
	private BookSearchRepository bookSearchRepository;
	
	@Override
	public List<BookTo> findBookByCriteria(BookSearchCriteria criteria) {
		return BookMapper.map2To(bookSearchRepository.findBookByCriteria(criteria));
	}

}
