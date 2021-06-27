package com.leantech.ejercicio.app.empresa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.leantech.ejercicio.app.empresa.models.entities.Employee;
import com.leantech.ejercicio.app.empresa.models.entities.Person;
import com.leantech.ejercicio.app.empresa.models.entities.Position;
import com.leantech.ejercicio.app.empresa.models.services.IEmployeeService;
import com.leantech.ejercicio.app.empresa.models.services.IPersonService;
import com.leantech.ejercicio.app.empresa.models.services.IPositionService;



@RestController
@RequestMapping("/api-employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private IPersonService personService;
	
	//@GetMapping("/employee/position/{position}/name/{name}")
	@GetMapping("/employee")
	public List<Employee> index(@RequestParam(value = "position", required = false) String position, 
								@RequestParam(value = "name", required = false) String name){
		
		
		List<Employee> employees = 
				employeeService.findAll()
				.stream()
				.filter( emp -> { 
					return position == null ? true : emp.getPosition().getName().equals(position.toUpperCase());				
				})
				.filter(emp -> {
					return name == null ? true : emp.getPerson().getName().toUpperCase().contains(name.toUpperCase());
				})
				.collect(Collectors.toList());
		return 	employees;				
		
	}
	
	
	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Employee employee) {
		Map<String, Object> response= new HashMap<>();		
		Employee employeeNew = null;
		
		try {
			employeeNew = employeeService.save(employee);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error creando el empleado ");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Empleado creado exitosamente");
		response.put("employee", employeeNew);
		return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<?> editar(@RequestBody Employee employee, @PathVariable Long id) {

		Employee e = this.employeeService.findOne(id);

		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		
		e.setSalary(employee.getSalary());
		System.out.print(employee.getPosition());
		Position p = positionService.findOne(employee.getPosition().getId());
		Person per = personService.findOne(employee.getPerson().getId());
		
		if( per != null ) {
			e.getPerson().setName(employee.getPerson().getName());
			e.getPerson().setCellPhone(employee.getPerson().getCellPhone());
			e.getPerson().setLastName(employee.getPerson().getLastName());
		}
		
		if(p != null && p.getId() != e.getPosition().getId()) {
			e.setPosition(p);
		}
		
		Map<String, Object> response= new HashMap<>();
		e = this.employeeService.save(e);
		response.put("mensaje", "Empleado actualizado exitosamente");
		response.put("employee", e);		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		Employee e = this.employeeService.findOne(id);
		
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		
		this.employeeService.delete(e);
		
		return ResponseEntity.noContent().build();
	}
	
}
