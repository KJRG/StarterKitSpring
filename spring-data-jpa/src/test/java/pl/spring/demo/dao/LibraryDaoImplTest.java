package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.LibraryDao;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {

	@Autowired
	private LibraryDao libraryDao;
	
	@Test
	public void testShoudlFindLibraryByName() {
		//given
		String name = "Biblioteka Wroc";
		//when
		List<LibraryEntity> libraryEntities = libraryDao.findLibrariesByName(name);
		//then
		assertNotNull(libraryEntities);
		assertFalse(libraryEntities.isEmpty());
		assertEquals("Biblioteka Wrocławska", libraryEntities.get(0).getName());
	}

	@Test
	public void testShoudlFindLibraryByPrefixOfMulitpleLibrariesNames() {
		//given
		String prefix = "bibl";
		String name = "Biblioteka Wrocławska";
		Boolean libraryFound = false;
		//when
		List<LibraryEntity> libraryEntities = libraryDao.findLibrariesByName(prefix);
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
