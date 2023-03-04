package it.discovery.redis.repository.redisson;

import it.discovery.redis.model.Book;
import it.discovery.redis.repository.BookRepository;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.util.NumberUtils;

import java.util.List;
import java.util.stream.Stream;

public class RedissonJsonBookRepository implements BookRepository, AutoCloseable {

    private final static String PREFIX = "books:";

    private final RedissonClient redissonClient;

    private final Codec JSON = new JsonJacksonCodec();

    public RedissonJsonBookRepository(String host, int port) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://" + host + ":" + port);
        redissonClient = Redisson.create(config);
    }

    private RBucket<Book> getBucket(String id) {
        return redissonClient.getBucket(PREFIX + id, JSON);
    }

    @Override
    public Book save(Book book) {
        RBucket<Book> bucket = getBucket(book.getId() + "");
        bucket.set(book);
        return book;
    }

    @Override
    public Book getOne(int id) {
        RBucket<Book> bucket = getBucket(id + "");
        return bucket.get();
    }

    @Override
    public List<Book> findAll() {
        RKeys keys = redissonClient.getKeys();
        return keys.getKeysStreamByPattern(PREFIX + "*", 10).map(id ->
                getOne(NumberUtils.parseNumber(id.replaceAll(PREFIX, ""),
                Integer.class))).toList();
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

    }
}
