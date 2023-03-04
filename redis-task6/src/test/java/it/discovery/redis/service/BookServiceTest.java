package it.discovery.redis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.discovery.redis.model.Book;

public class BookServiceTest {

	BookService bookService;
	
	@Test
	void saveBook_findByName_success() {
		Book book = new Book();
		book.setNameEn("JPA");
		bookService.saveBook(book);
		
		List<Book> books = bookService.findByName("JPA");
		assertEquals(1, books.size());
		assertEquals("JPA", books.get(0).getNameEn());
	}

}
