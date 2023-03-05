package redis.repository.spring.om;

import it.discovery.redis.model.ShoppingCart;
import it.discovery.redis.repository.spring.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.BaseSpringOmTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShoppingCartRepositoryTest extends BaseSpringOmTest {

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
