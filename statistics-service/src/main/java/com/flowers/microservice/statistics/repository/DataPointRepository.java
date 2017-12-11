package com.flowers.microservice.statistics.repository;

import com.flowers.microservice.statistics.domain.timeseries.DataPoint;
import com.flowers.microservice.statistics.domain.timeseries.DataPointId;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, DataPointId> {

	@HystrixCommand(fallbackMethod = "reliable")
	List<DataPoint> findByIdAccount(String account);

	
	public default List<DataPoint> reliable() {

		List<DataPoint> list = new ArrayList<DataPoint>();
		DataPoint dta = new DataPoint();
		list.add(dta);
		
		return list;
	}
	
}
