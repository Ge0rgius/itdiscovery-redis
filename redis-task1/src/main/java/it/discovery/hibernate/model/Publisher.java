package it.discovery.hibernate.model;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Book publisher
 * 
 */
@Getter
@Setter
@Table
@Entity
public class Publisher extends BaseEntity {
	private String name;

	@OneToMany(mappedBy = "publisher")
	private List<Book> books;

	@Embedded
	private Contact contact;
}
