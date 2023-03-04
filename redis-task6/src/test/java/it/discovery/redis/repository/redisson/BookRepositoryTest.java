package it.discovery.redis.repository.redisson;

import it.discovery.redis.BaseRedisTest;
import it.discovery.redis.model.Book;
import it.discovery.redis.model.Person;
import it.discovery.redis.model.Publisher;
import it.discovery.redis.model.Review;
import it.discovery.redis.repository.BookRepository;
import it.discovery.redis.repository.jedis.JedisJsonBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookRepositoryTest extends BaseRedisTest {

    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository = new RedissonJsonBookRepository("localhost", redis.getMappedPort(6379));
    }

    @Test
    void save_validBook_success() {
        Book book = new Book();
        book.setId(1);
        book.setNameEn("Redis");
        book.setPages(300);
        bookRepository.save(book);

        Book book1 = bookRepository.getOne(book.getId());
        assertNotNull(book1);
        assertEquals(book.getNameEn(), book1.getNameEn());
        assertEquals(book.getPages(), book1.getPages());
    }

    @Test
    @Disabled
    void findWithReviews_returnsSingleBook() {
        Person author = new Person();
        author.setName("Gavin King");
        Publisher publisher = new Publisher();
        publisher.setName("Packt");

        Book book1 = new Book();
        book1.setNameEn("JPA");
        book1.setAuthorId("Unknown");
        book1.setPublisherId("Packt");

        Book book2 = new Book();
        book2.setNameEn("Hibernate");
        book2.setAuthorId("N/A");
        book2.setPublisherId("O'Reilly");

        Review review = new Review();
        review.setComment("good");
        review.setRate(10);
        book2.addReview(review);
        //bookRepository.saveAll(List.of(book1, book2));

        List<Book> books = bookRepository.findWithReviews();
        assertEquals(1, books.size());
        assertEquals("Hibernate", books.get(0).getNameEn());
    }

}
