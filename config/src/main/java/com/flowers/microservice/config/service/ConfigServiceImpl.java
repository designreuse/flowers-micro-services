/**
 * 
 */
package com.flowers.microservice.config.service;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import com.flowers.microservice.config.repository.ConfigJpaRepository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class ConfigServiceImpl implements ConfigService {
	
	@FunctionalInterface
	public interface Function2<One, Two> {
	    public Two apply(One one, Two two);
	}		

	public final Function<Iterable<String>, String[]> lister = list -> ((List<String>)list).stream().toArray(String[]::new); 
	public final Function2<Iterable<String>,String> search = (list,key) ->  Arrays.asList(lister.apply(list)).stream().filter(s -> s.contains(key)).findFirst().orElseGet(String::new);
	
	@Autowired ConfigJpaRepository repository;
	
	public String getConfig(final String key){
		
		return search.apply(repository.findAll(), key);
	}
	
	public void setConfig(String key, String value){
		
		repository.save(String.format("%s,%s", key, value));
	}

}
	
