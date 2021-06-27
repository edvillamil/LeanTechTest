package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import com.leantech.ejercicio.app.empresa.models.entities.Position;

public interface IPositionService {
	
	public List<Position> findAll();
	
	public Position findOne(Long id);

}
