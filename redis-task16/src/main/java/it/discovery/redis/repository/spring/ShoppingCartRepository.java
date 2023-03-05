package it.discovery.redis.repository.spring;

import com.redis.om.spring.repository.RedisDocumentRepository;
import it.discovery.redis.model.ShoppingCart;

public interface ShoppingCartRepository extends RedisDocumentRepository<ShoppingCart, String> {
}
