package com.crossover.techtrial.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.repositories.TopDriverRepository;

@RunWith(MockitoJUnitRunner.class)
public class TopDriveServiceMockTest {
	@InjectMocks
	TopDriverServiceImpl topDriverServiceImpl;
	@Mock
	TopDriverRepository topDriverRepository;

	@Test
	public void topDriversTest() {
		List<TopDriverDTO> list = new ArrayList<>();
		list.add(new TopDriverDTO("Olcay", "olcay.bilir@gmail.com", BigDecimal.valueOf(50), BigDecimal.valueOf(100),
				BigDecimal.valueOf(80)));
		
		when(topDriverRepository.getTopDriverOrRider(LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30),
					LocalDateTime.of(2017, Month.JANUARY, 1, 10, 10, 30), 5L)).thenReturn(list);

		List<TopDriverDTO> dtoList = topDriverServiceImpl.getTopDrivers(LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30),
				LocalDateTime.of(2017, Month.JANUARY, 1, 10, 10, 30), 5L);
		Assert.assertNotNull(dtoList);
		Assert.assertEquals(dtoList.size(), 1);
		Assert.assertEquals(dtoList.get(0).getName(), "Olcay");

	}
}
