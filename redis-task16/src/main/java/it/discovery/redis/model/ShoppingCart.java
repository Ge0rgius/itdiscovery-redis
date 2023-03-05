package it.discovery.redis.model;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Document("carts")
@Getter
@Setter
public class ShoppingCart {

    @Id
    private String userId;

    private List<String> books;

    private LocalDateTime created;
}
