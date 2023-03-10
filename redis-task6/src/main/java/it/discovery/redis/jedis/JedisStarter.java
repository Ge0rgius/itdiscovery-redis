package it.discovery.redis.jedis;

import it.discovery.redis.model.Book;
import it.discovery.redis.repository.PersonRepository;
import it.discovery.redis.repository.jedis.JedisHashBookRepository;
import it.discovery.redis.repository.jedis.JedisJsonBookRepository;
import it.discovery.redis.repository.jedis.JedisPersonRepository;

import java.util.List;

public class JedisStarter {
	
	public static void main(String[] args) throws Exception {

		try(JedisHashBookRepository bookRepository = new JedisHashBookRepository("localhost", 6379)) {
			Book book = new Book();
			book.setId(1);
			book.setNameEn("Redis");
			book.setPages(300);
			bookRepository.save(book);

			Book book1 = bookRepository.getOne(book.getId());
			System.out.println(book1);
			List<Book> books = bookRepository.findAll();
			System.out.println(books);
		}

		PersonRepository personRepository = new JedisPersonRepository();
		
	}

}
