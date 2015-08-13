package pl.spring.demo.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="LIBRARY_ID")
	private Long id;
	@Column(nullable = false, length = 50)
	private String name;
	
//	@OneToMany
//	@Column(name = "library_id")
	@OneToMany(mappedBy = "library")
	private Set<BookEntity> books = new HashSet<>();
	
	//for hibernate
	public LibraryEntity() {
		
	}

	public LibraryEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
