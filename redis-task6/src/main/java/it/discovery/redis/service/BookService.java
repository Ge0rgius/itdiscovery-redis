package it.discovery.redis.service;

import it.discovery.redis.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BookService {

    private final RedisTemplate<Integer, ?> redisTemplate;

    private final HashMapper mapper = new ObjectHashMapper();

    public List<Book> findByName(String name) {
        //TODO
        return null;
    }

    public void saveBook(Book book) {
        HashOperations<Integer, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, String> map = mapper.toHash(book);
        hashOperations.putAll(book.getId(), map);
    }

    public Book findById(int id) {
        HashOperations<Integer, String, String> hashOperations = redisTemplate.opsForHash();
        Map<String, String> map = hashOperations.entries(id);
        return (Book) mapper.fromHash(map);
    }

    /**
     * Returns all the books where number of pages is greater than pages parameter
     *
     * @param name
     * @return
     */
    public List<Book> findByPagesGreaterThan(int pages) {
        //TODO implement
        return null;
    }
}
