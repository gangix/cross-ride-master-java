/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Ride;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {

	@Query(value = "SELECT r.* FROM crossride.ride r \n" + "where (r.start_time between :starttime and :endtime\n"
			+ " or r.end_time between :starttime and :endtime)"
			+ " and (r.driver_id=:personId or r.rider_id=:personId)", nativeQuery = true)
	public List<Ride> getTimedRide(@Param("starttime") LocalDateTime startTime, @Param("endtime") LocalDateTime endTime,
			@Param("personId") Long personId);

}
