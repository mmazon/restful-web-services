package com.mazon.spring.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean(){
		SomeBeanFilter sb = new SomeBeanFilter("value1", "value2", "value3");
		return createFilterSomeBean(Stream.of("field1", "field2").collect(Collectors.toCollection(HashSet::new)), sb);
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean(){
		List<SomeBeanFilter> list = new ArrayList<>();
		list.add(new SomeBeanFilter("value1", "value2", "value3"));
		list.add(new SomeBeanFilter("value11", "value22", "value33"));
		return createFilterSomeBean(Stream.of("field2", "field3").collect(Collectors.toCollection(HashSet::new)), list);
	}
	
	private MappingJacksonValue createFilterSomeBean(Set<String> propertiesToIncludeFilter, Object objectFilter){
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertiesToIncludeFilter);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue maping = new MappingJacksonValue(objectFilter);
		maping.setFilters(filters);
		return maping;
	}

}
