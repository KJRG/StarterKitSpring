package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testShouldFindLibraryByName() {
		//given
		String name = "biblioteka wroc";
		//when
		List<LibraryEntity> libraryEntities = libraryService.findLibraryByName(name);
		//then
		assertNotNull(libraryEntities);
		assertFalse(libraryEntities.isEmpty());
		assertEquals("Biblioteka Wrocławska", libraryEntities.get(0).getName());
	}

	@Test
	public void testShouldFindLibraryByPrefixOfMultipleLibrariesNames() {
		//given
		String prefix = "bibl";
		String name = "Biblioteka Wrocławska";
		Boolean libraryFound = false;
		//when
		List<LibraryEntity> libraryEntities = libraryService.findLibraryByName(prefix);
		ListIterator<LibraryEntity> iter = libraryEntities.listIterator();
		//then
		assertNotNull(libraryEntities);
		assertFalse(libraryEntities.isEmpty());
		while(iter.hasNext()) {
			if(iter.next().getName().equals(name)) {
				libraryFound = true;
				break;
			}
		}
		assertTrue(libraryFound);
	}

	@Test
	public void testShouldDeleteAllBooksAssignedToLibrary() {
		//given
		Long libraryId = 13L;
		Long bookCountBeforeDeletingLibrary = bookRepository.count();
		//when
		libraryService.deleteLibrary(libraryId);
		Long bookCountAfterDeletingLibrary = bookRepository.count();
		//then
		assertTrue(bookCountAfterDeletingLibrary < bookCountBeforeDeletingLibrary);
	}
}
