package redis;

import it.discovery.redis.RedisJSONApplication;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//@DataRedisTest
@SpringBootTest(classes = RedisJSONApplication.class)
@Testcontainers
public class BaseSpringOmTest {
	@Container
	static GenericContainer<?> redis = new GenericContainer<>("redis/redis-stack").withExposedPorts(6379);

	@DynamicPropertySource
	static void redisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.redis.port", () -> redis.getMappedPort(6379));
	}
}
