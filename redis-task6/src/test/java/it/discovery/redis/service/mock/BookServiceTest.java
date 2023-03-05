package it.discovery.redis.service.mock;

import com.github.fppt.jedismock.RedisServer;
import it.discovery.redis.BaseSpringDataRedisTest;
import it.discovery.redis.model.Book;
import it.discovery.redis.service.BookService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataRedisTest
public class BookServiceTest {

	static RedisServer server;

	@Autowired
    BookService bookService;

	@BeforeAll
	static void setup() throws IOException {
		server = RedisServer.newRedisServer().start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		if(server != null) {
			server.stop();
		}
	}

	@DynamicPropertySource
	static void redisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.redis.port", () -> server.getBindPort());
	}
	
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
