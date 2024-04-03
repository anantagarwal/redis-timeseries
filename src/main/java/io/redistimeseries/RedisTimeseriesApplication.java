package io.redistimeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDate;

@SpringBootApplication
public class RedisTimeseriesApplication implements CommandLineRunner {

	@Autowired
	RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RedisTimeseriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDate date_1 = LocalDate.of(2024, 1, 1);
		LocalDate date_2 = LocalDate.of(2024, 1, 10);
		LocalDate date_3 = LocalDate.of(2024, 1, 20);


		redisTemplate.opsForHash().put("labels", "customer.name", "First Name");
		redisTemplate.opsForHash().put("labels", "customer.lastname", "Last Name");
		redisTemplate.opsForHash().put("labels", "customer.mobile", "Mobile Number");


		redisTemplate.opsForZSet().add("labels_timestamps", "customer.name", date_1.toEpochDay());
		redisTemplate.opsForZSet().add("labels_timestamps", "customer.lastname", date_2.toEpochDay());
		redisTemplate.opsForZSet().add("labels_timestamps", "customer.mobile", date_3.toEpochDay());
	}
}
