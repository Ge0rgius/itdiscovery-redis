package it.discovery.redis.repository;

import java.util.List;

import it.discovery.redis.model.Person;

public interface PersonRepository {
	
	/**
	 * Returns all the persons sorted by name
	 * @return
	 */
	List<Person> findByOrderByNameAsc();
}
