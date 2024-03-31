package io.redistimeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisTimeseriesApplication implements CommandLineRunner {

	@Autowired
	RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RedisTimeseriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		redisTemplate.opsForHash().put("labels", "customer.name", "First Name");
		long currentTimestamp = System.currentTimeMillis();
		redisTemplate.opsForZSet().add("labels_timestamps", "customer.name", currentTimestamp);
	}
}
