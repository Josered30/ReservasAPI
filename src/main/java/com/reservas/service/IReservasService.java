package com.reservas.service;

import java.util.Date;
import java.util.List;

import com.reservas.entities.Reserva;

public interface IReservasService extends CrudService<Reserva>{
	List<Reserva> findByFechaIngresoBetween(Date fechaInicio, Date fechaFin) throws Exception;
}
