package com.reservas.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.entities.Reserva;
import com.reservas.repository.IReservasRepository;
import com.reservas.service.IReservasService;

@Service
@Transactional(readOnly = true)
public class ReservasServiceImpl implements IReservasService {

	@Autowired
	IReservasRepository reservasRepository;
	
	@Transactional
	@Override
	public Reserva save(Reserva t) throws Exception {
		// TODO Auto-generated method stub
		return reservasRepository.save(t);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		reservasRepository.deleteById(id);
		
	}

	
	@Override
	public Optional<Reserva> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return reservasRepository.findById(id);
	}

	
	@Override
	public List<Reserva> findAll() throws Exception {
		// TODO Auto-generated method stub
		return reservasRepository.findAll();
	}


	@Override
	public List<Reserva> findByFechaIngresoBetween(Date fechaInicio, Date fechaFin) throws Exception {
		// TODO Auto-generated method stub
		return reservasRepository.findByFechaIngresoBetween(fechaInicio, fechaFin);
	}

	
}
