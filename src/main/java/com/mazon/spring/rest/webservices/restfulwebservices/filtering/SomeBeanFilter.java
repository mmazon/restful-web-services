package com.mazon.spring.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter") //dynamic filtering - para cada method rest resource/controler
public class SomeBeanFilter {
	
	private String field1;
	private String field2;
	
	//@JsonIgnore static filter - filtra fora de todos os method rest resource/controler
	private String field3;
	
	public SomeBeanFilter(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}
