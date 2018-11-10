package com.crossover.techtrial.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Repository;

import com.crossover.techtrial.dto.TopDriverDTO;

@Repository
public class TopDriverRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<TopDriverDTO> getTopDriverOrRider(LocalDateTime startDate,LocalDateTime endDate,long limit) {
		Query tuples = entityManager.createNativeQuery(
				"SELECT p.name,p.email,TIME_TO_SEC(sum(TIMEDIFF(r.end_time,r.start_time)))/60 as sum,\r\n"
						+ "TIME_TO_SEC(max(TIMEDIFF(r.end_time,r.start_time)))/60 as max,avg(r.distance) as avg \r\n"
						+ "FROM crossride.ride r,\r\n" + "crossride.person p\r\n"
						+ "where (p.id = r.driver_id or p.id = r.rider_id)\r\n"
						+ "and r.start_time>:starttime and r.end_time<:endtime "
						+" group by p.id\n"
						+ "order by max desc\r\n" + "Limit :limit\r\n",
				Tuple.class).setParameter("starttime", startDate)
				.setParameter("endtime", endDate)
				.setParameter("limit", limit);

		List<Tuple> resultList = tuples.getResultList();

		List<TopDriverDTO> list = new ArrayList<>();
		resultList.forEach(tuple -> {
			String name = ((String) tuple.get("name"));
			String email = ((String) tuple.get("email"));
			BigDecimal maxRideDurationInSecods = ((BigDecimal) tuple.get("max"));
			BigDecimal totalRideDurationInSeconds = ((BigDecimal) tuple.get("sum"));
			BigDecimal averageDistance = ((BigDecimal) tuple.get("avg"));
			TopDriverDTO topD = new TopDriverDTO(name, email, totalRideDurationInSeconds, maxRideDurationInSecods,
					averageDistance);
			list.add(topD);
		});
		return list;
	}

}
