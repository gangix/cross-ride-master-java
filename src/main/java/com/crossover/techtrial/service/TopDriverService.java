/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.List;

import com.crossover.techtrial.dto.TopDriverDTO;

/**
 * TopDriverService for rides and persons.
 * 
 * @author olcay
 *
 */
public interface TopDriverService {

	public List<TopDriverDTO> getTopDrivers(LocalDateTime startDate, LocalDateTime endDate, long limit);

}
