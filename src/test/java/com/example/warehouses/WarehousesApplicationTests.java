package com.example.warehouses;

import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.repository.WareHousesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WarehousesApplicationTests {
@Autowired
private WareHousesRepository wareHousesRepository;
	@Test
	void contextLoads() {
	}

}
