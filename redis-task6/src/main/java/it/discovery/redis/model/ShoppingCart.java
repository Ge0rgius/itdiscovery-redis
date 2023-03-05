package it.discovery.redis.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.List;

@RedisHash(value = "carts", timeToLive = 900)
@Getter
@Setter
public class ShoppingCart {

    @Id
    private String userId;

    private List<String> books;

    private LocalDateTime created;
}
