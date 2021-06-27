package com.leantech.ejercicio.app.empresa.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.leantech.ejercicio.app.empresa.models.entities.Person;

public interface IPersonDao extends CrudRepository<Person, Long>{

}
