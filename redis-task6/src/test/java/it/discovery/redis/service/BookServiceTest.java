package it.discovery.redis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import it.discovery.redis.BaseSpringDataRedisTest;
import org.junit.jupiter.api.Test;

import it.discovery.redis.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceTest extends BaseSpringDataRedisTest {

	@Autowired
	BookService bookService;
	
	@Test
	void saveBook_findById_success() {
		Book book = new Book();
		book.setNameEn("JPA");
		book.setId(1);
		bookService.saveBook(book);
		
		Book book1 = bookService.findById(book.getId());
		assertNotNull(book1);
		assertEquals("JPA", book1.getNameEn());
	}

}
