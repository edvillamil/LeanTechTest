package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leantech.ejercicio.app.empresa.model.dao.IEmployeeDao;
import com.leantech.ejercicio.app.empresa.models.entities.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findOne(Long id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

}
