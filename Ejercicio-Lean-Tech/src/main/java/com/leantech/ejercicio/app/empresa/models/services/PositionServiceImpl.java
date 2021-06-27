package com.leantech.ejercicio.app.empresa.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leantech.ejercicio.app.empresa.model.dao.IPositionDao;
import com.leantech.ejercicio.app.empresa.models.entities.Position;

@Service
public class PositionServiceImpl implements IPositionService {

	@Autowired
	private IPositionDao positionDao;
	
	@Override
	public List<Position> findAll() {
		return (List<Position>) positionDao.findAll();
	}

	@Override
	public Position findOne(Long id) {
		return positionDao.findById(id).orElse(null);
	}
	
	

}
