/**
 * 
 */
package com.crossover.techtrial.dto;

import java.math.BigDecimal;

/**
 * @author crossover
 *
 */
public class TopDriverDTO {

	/**
	 * Constructor for TopDriverDTO
	 * 
	 * @param name
	 * @param email
	 * @param totalRideDurationInMinutes
	 * @param maxRideDurationInMinutes
	 * @param averageDistance
	 */

	public TopDriverDTO() {

	}

	public TopDriverDTO(String name, String email, BigDecimal totalRideDurationInMinutes,
			BigDecimal maxRideDurationInMinutes, BigDecimal averageDistance) {
		super();
		this.name = name;
		this.email = email;
		this.totalRideDurationInMinutes = totalRideDurationInMinutes;
		this.maxRideDurationInMinutes = maxRideDurationInMinutes;
		this.averageDistance = averageDistance;
	}

	private String name;

	private String email;

	private BigDecimal totalRideDurationInMinutes;

	private BigDecimal maxRideDurationInMinutes;

	private BigDecimal averageDistance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalRideDurationInMinutes() {
		return totalRideDurationInMinutes;
	}

	public void setTotalRideDurationInMinutes(BigDecimal totalRideDurationInMinutes) {
		this.totalRideDurationInMinutes = totalRideDurationInMinutes;
	}

	public BigDecimal getMaxRideDurationInMinutes() {
		return maxRideDurationInMinutes;
	}

	public void setMaxRideDurationInMinutes(BigDecimal maxRideDurationInMinutes) {
		this.maxRideDurationInMinutes = maxRideDurationInMinutes;
	}

	public BigDecimal getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(BigDecimal averageDistance) {
		this.averageDistance = averageDistance;
	}

}
