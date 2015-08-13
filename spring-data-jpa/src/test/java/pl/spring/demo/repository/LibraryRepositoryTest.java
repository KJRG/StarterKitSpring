package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Test
	public void shouldFindLibraryByName() {
		//given
		String name = "biblioteka wroc";
		//when
		List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(name);
		//then
		assertNotNull(libraryEntities);
		assertFalse(libraryEntities.isEmpty());
		assertEquals("Biblioteka Wrocławska", libraryEntities.get(0).getName());
	}

	@Test
	public void shouldFindLibraryByPrefixOfMultipleLibrariesNames() {
		//given
		String prefix = "bibl";
		String name = "Biblioteka Wrocławska";
		Boolean libraryFound = false;
		//when
		List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(prefix);
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
		if(!libraryFound) {
			fail("Library not found.");
		}
	}

}
