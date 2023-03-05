package it.discovery.redis.repository.spring.data;

import it.discovery.redis.BaseSpringDataRedisTest;
import it.discovery.redis.model.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShoppingCartRepositoryTest extends BaseSpringDataRedisTest {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Test
    void save_validCart_success() {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId("123");
        cart.setBooks(List.of("isbn:123"));
        shoppingCartRepository.save(cart);

        ShoppingCart cart1 = shoppingCartRepository.findById(cart.getUserId()).get();
        assertNotNull(cart1);
        assertEquals(cart.getBooks(), cart1.getBooks());
    }
}
