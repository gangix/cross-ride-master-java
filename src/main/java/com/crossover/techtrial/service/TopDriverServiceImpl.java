/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.repositories.TopDriverRepository;

/**
 * @author olcay
 *
 */
@Service
public class TopDriverServiceImpl implements TopDriverService {

	private final TopDriverRepository topDriverRepository;

	@Autowired
	public TopDriverServiceImpl(TopDriverRepository topDriverRepository) {
		this.topDriverRepository = topDriverRepository;
	}

	@Override
	public List<TopDriverDTO> getTopDrivers(LocalDateTime startDate, LocalDateTime endDate, long limit) {
		return topDriverRepository.getTopDriverOrRider(startDate, endDate, limit);
	}

}
