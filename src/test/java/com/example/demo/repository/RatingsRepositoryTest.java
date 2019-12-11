package com.example.demo.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testcontainers.containers.GenericContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class RatingsRepositoryTest {

    final String talkId = "testcontainers";

    RatingsRepository repository;

    @Rule
    public GenericContainer redis = new GenericContainer("redis:3-alpine")
            .withExposedPorts(6379);

    @Before
    public void setUp() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                redis.getContainerIpAddress(),
                redis.getFirstMappedPort()
        );
        connectionFactory.afterPropertiesSet();
        repository = new RatingsRepository(new StringRedisTemplate(connectionFactory));
    }

    @Test
    public void testEmptyIfNoKey() {
        assertThat(repository.findAll(talkId)).isEmpty();
    }

    @Test
    public void testLimits() {
        repository.redisTemplate.opsForHash()
                .put(repository.toKey(talkId), "5", Long.MAX_VALUE + "");

        repository.add(talkId, 5);
    }
}