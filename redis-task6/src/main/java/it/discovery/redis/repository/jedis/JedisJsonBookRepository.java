package it.discovery.redis.repository.jedis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.redis.model.Book;
import it.discovery.redis.repository.BookRepository;
import org.springframework.util.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.ArrayList;
import java.util.List;

public class JedisJsonBookRepository implements BookRepository, AutoCloseable {

    private final static String PREFIX = "books:";

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public JedisJsonBookRepository(String host, int port) {
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
        if(json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        ScanParams scanParams = new ScanParams().count(20).match(PREFIX + "*");
        String cursor = ScanParams.SCAN_POINTER_START;

        List<Book> books = new ArrayList<>();
        do {
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
            List<String> ids = scanResult.getResult();
            books.addAll(ids.stream().map(id -> getOne(NumberUtils.parseNumber(id.replaceAll(PREFIX, ""),
                    Integer.class))).toList());

            cursor = scanResult.getCursor();
        } while (!cursor.equals(ScanParams.SCAN_POINTER_START));
        return books;
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

    @Override
    public void close() throws Exception {
        jedis.close();
    }
}
