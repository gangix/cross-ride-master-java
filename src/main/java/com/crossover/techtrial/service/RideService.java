/**
 * 
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Ride;

/**
 * RideService for rides.
 * 
 * @author crossover
 *
 */
public interface RideService {

	public Ride save(Ride ride) throws Exception;

	public Ride findById(Long rideId);

}
