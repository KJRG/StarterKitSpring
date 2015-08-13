package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.entity.LibraryEntity;

public interface LibraryService {
	List<LibraryEntity> findLibraryByName(String name);
	
	void deleteLibrary(Long id);
}
