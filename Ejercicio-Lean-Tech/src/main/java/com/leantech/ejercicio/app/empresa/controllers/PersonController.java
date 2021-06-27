package com.leantech.ejercicio.app.empresa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leantech.ejercicio.app.empresa.models.entities.Person;
import com.leantech.ejercicio.app.empresa.models.services.IPersonService;

@RestController
@RequestMapping("/api-person")
public class PersonController {

	
	@Autowired
	private IPersonService personService;
	
	@GetMapping("/person")
	public List<Person> index(){
		return personService.findAll();		
	}
	
	@PostMapping("/person")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Person person) {
		Map<String, Object> response= new HashMap<>();		
		Person personNew = null;
		
		try {
			personNew = personService.save(person);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error creando la persona ");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Persona creada exitosamente");
		response.put("cliente", personNew);
		return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
