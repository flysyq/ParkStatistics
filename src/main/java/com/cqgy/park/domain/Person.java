/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:01:03
 * @类名 Person.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:01:03
 *   修改描述
 */
package com.cqgy.park.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery",
query="select p from Person p where p.name=?1 and address=?2")
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer age;
	private String address;
	
	public Person(){
		super();
	}
	
	public Person(String name, Integer age){
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person(Long id,String name,Integer age,String address){
		super();
		this.id=id;
		this.name = name;
		this.age = age;
		this.address=address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
}

