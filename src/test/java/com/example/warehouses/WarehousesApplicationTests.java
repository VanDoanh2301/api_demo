package com.example.warehouses;

import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.repository.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WarehousesApplicationTests {
@Autowired
private ScoreRepository scoreRepository;
	@Test
	void contextLoads() {
		List<Score> scores =scoreRepository.findDistinctByMonthLikeOrderByScoreDesc("01");
		System.out.println(scores);

	}

}
