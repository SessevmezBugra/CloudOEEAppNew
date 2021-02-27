package com.oee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class MaindataServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenCreatesEmptyOptional_thenCorrect() {
		Optional<String> empty = Optional.empty();
		assertFalse(empty.isPresent());
	}

}
