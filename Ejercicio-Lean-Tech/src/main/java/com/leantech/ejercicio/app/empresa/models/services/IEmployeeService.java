package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import com.leantech.ejercicio.app.empresa.models.entities.Employee;

public interface IEmployeeService {
	
	public List<Employee> findAll();

	public Employee save(Employee employee);
	
	public Employee findOne(Long id);
	
	public void delete(Employee employee);

}
