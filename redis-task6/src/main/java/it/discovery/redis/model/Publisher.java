package it.discovery.redis.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Book publisher
 * 
 */
@Getter
@Setter
public class Publisher extends BaseEntity {
	private String name;

	private List<Book> books;

	private Contact contact;
}
