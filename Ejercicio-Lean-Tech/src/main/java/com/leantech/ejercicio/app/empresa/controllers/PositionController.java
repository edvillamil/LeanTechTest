package com.leantech.ejercicio.app.empresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leantech.ejercicio.app.empresa.models.entities.Position;
import com.leantech.ejercicio.app.empresa.models.services.IPositionService;

@RestController
@RequestMapping("/api-position")
public class PositionController {
	
	@Autowired
	private IPositionService postitionService;
	
	//@GetMapping("/employee/position/{position}/name/{name}")
	@GetMapping("/position")
	public List<Position> get(){	
		
		List<Position> positions = postitionService.findAll();
		
		/*for( Position p : positions ) {
			p.set
		}*/
		
		return positions;
	}

}
