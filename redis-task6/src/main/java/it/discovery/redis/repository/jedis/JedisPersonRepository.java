package it.discovery.redis.repository.jedis;

import it.discovery.redis.model.Person;
import it.discovery.redis.repository.PersonRepository;

import java.util.List;

public class JedisPersonRepository implements PersonRepository {
    @Override
    public List<Person> findByOrderByNameAsc() {
        return null;
    }
}
