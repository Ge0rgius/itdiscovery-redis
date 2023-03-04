package it.discovery.redis.repository.jedis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.redis.model.Book;
import it.discovery.redis.repository.BookRepository;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisBookRepository implements BookRepository {

    private final static String PREFIX = "books:";

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public JedisBookRepository(String host, int port) {
        jedis = new Jedis(host, port);
        objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    @Override
    public Book save(Book book) {
        try {
            jedis.set(PREFIX + book.getId(), objectMapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public Book getOne(int id) {
        String json = jedis.get(PREFIX + id);
        try {
            return objectMapper.readValue(json, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByName(String name) {
        return null;
    }

    @Override
    public List<Book> findWithReviews() {
        return null;
    }

    @Override
    public int findTotalPages() {
        return 0;
    }
}
