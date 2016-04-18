/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:09:38
 * @类名 PersonController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:09:38
 *   修改描述
 */
package com.cqgy.park.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cqgy.park.dao.ClassStudentService;
import com.cqgy.park.dao.PersonRepository;
import com.cqgy.park.dao.PersonService;
import com.cqgy.park.domain.Person;
import com.cqgy.park.qresult.ClassStudent;


@RestController
public class PersonController {
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ClassStudentService classStudentService;
	
	@RequestMapping("/save")
	public Person save(String name,String address,Integer age){
		Person p = personRepository.save(new Person(null,name,age,address));
		return p;
	}
	
	@RequestMapping("/q1")
	public List<Person> q1(String address){
		System.out.println("Hello q1");
		List<Person> people = personRepository.findByAddress(address);
		return people;
	}
	
	@RequestMapping("/q2")
	public Person q2(String name,String address){
		Person people = personRepository.findByNameAndAddress(name,address);
		return people;
	}
	
	@RequestMapping("/q3")
	public Person q3(String name,String address){
		Person people = personRepository.withNameAndAddressQuery(name, address);
		return people;
	}
	
	@RequestMapping("/q4")
	public List<Person> q4(String name,String address){
		List<Person> people = personRepository.withNameAndAddressNamedQuery(name, address);
		return people;
	}
	@RequestMapping("/q5")
	public List<Person> q5(String name,String address){
		List<Person> people = personRepository.findByNameContainingAndAddressContaining(name, address);
		return people;
	}
	@RequestMapping("/q6")
	public List<ClassStudent> q5(Integer class_id){
		List<ClassStudent> student = classStudentService.getStudentByClass(class_id);
		return student;
	}
	
	@RequestMapping("/sort")
	public List<Person> sort(){
		List<Person> people = personRepository.findAll(new Sort(Direction.ASC, "age"));
		return people;
	}
	
	@RequestMapping("/page")
	public Page<Person> page(){
		Page<Person> pagePeople = personRepository.findAll(new PageRequest(1,2));
		return pagePeople;
	}
	
	@RequestMapping("/search")
	public List<Person> search(String name,String address,Integer page,Integer page_num){
		
		return personService.getList(page, page_num, address,name);
		
	}
}
