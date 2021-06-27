package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leantech.ejercicio.app.empresa.model.dao.IPersonDao;
import com.leantech.ejercicio.app.empresa.models.entities.Person;

@Service
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private IPersonDao personDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return (List<Person>) personDao.findAll();
	}

	@Override
	@Transactional
	public Person save(Person employee) {
		return personDao.save(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public Person findOne(Long id) {
		return personDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personDao.deleteById(id);
	}

}
