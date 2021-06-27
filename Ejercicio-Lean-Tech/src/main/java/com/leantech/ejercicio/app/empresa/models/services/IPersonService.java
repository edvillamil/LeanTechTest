package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import com.leantech.ejercicio.app.empresa.models.entities.Person;

	public interface IPersonService {
	
	public List<Person> findAll();

	public Person save(Person employee);
	
	public Person findOne(Long id);
	
	public void delete(Long id);

}
