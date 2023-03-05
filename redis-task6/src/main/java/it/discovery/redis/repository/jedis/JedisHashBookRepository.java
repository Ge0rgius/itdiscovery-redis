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
import java.util.Map;
import java.util.stream.Collectors;

public class JedisHashBookRepository implements BookRepository, AutoCloseable {

    private final static String PREFIX = "book-hash:";

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public JedisHashBookRepository(String host, int port) {
        jedis = new Jedis(host, port);
        objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    @Override
    public Book save(Book book) {
        Map<String, String> map = objectMapper.convertValue(book, Map.class);
        jedis.hset(PREFIX + book.getId(), map.entrySet().stream().filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return book;
    }

    @Override
    public Book getOne(int id) {
        Map<String, String> map = jedis.hgetAll(PREFIX + id);
        if(map == null || map.isEmpty()) {
            return null;
        }
        return objectMapper.convertValue(map, Book.class);
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
        //TODO
        return null;
    }

    @Override
    public List<Book> findWithReviews() {
        //TOOD
        return null;
    }

    @Override
    public int findTotalPages() {
        //TODO
        return 0;
    }

    @Override
    public void close() throws Exception {
        jedis.close();
    }
}
