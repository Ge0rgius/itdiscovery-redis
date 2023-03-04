package it.discovery.redis.jedis;

import it.discovery.redis.repository.BookRepository;
import it.discovery.redis.repository.PersonRepository;
import it.discovery.redis.repository.jedis.JedisBookRepository;
import it.discovery.redis.repository.jedis.JedisPersonRepository;

public class JedisStarter {
	
	public static void main(String[] args) {

		BookRepository bookRepository = new JedisBookRepository("localhost", 6379);

		PersonRepository personRepository = new JedisPersonRepository();
		
	}

}
