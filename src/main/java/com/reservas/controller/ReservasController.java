package com.reservas.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reservas.entities.Cliente;
import com.reservas.entities.Reserva;
import com.reservas.service.IClienteService;
import com.reservas.service.IReservasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/reservas")
@Api(tags = "Reservas", value ="Servicio Web RESTFull de Reservas")
public class ReservasController {
	
	@Autowired
	private IReservasService reservaService;
	
	@Autowired
	private IClienteService clienteService;


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Reservas", notes ="Servicio para listar todas los reservas")
	@ApiResponses({ 
		@ApiResponse(code=201,message = "Reservas encontradas"),
		@ApiResponse(code=404,message = "Resrvas no encontradas")		
	})
	public ResponseEntity<List<Reserva>> findAll(){
		try {
			List<Reserva> reservas  = new ArrayList<>();
			reservas = reservaService.findAll();
			return new ResponseEntity<List<Reserva>>(reservas,HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Reserva>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "searchById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Reservas por Id", notes ="Método para buscar reservas por Id")
	@ApiResponses({ 
		@ApiResponse(code=201,message = "Reserva encontrada"),
		@ApiResponse(code=404,message = "Reserva no encontrada")		
	})
	public ResponseEntity<Reserva> findById(@PathVariable("id") Integer id){
		try {
			Optional<Reserva> reserva =reservaService.findById(id);
			
			if(!reserva.isPresent()){
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Reserva>(reserva.get(),HttpStatus.OK);
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value = "searchByFecha/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Reservas por fecha", notes ="Método para buscar reservas por fecha")
	@ApiResponses({ 
		@ApiResponse(code=201,message = "Reserva encontrada"),
		@ApiResponse(code=404,message = "Reserva no encontrada")		
	})
	public ResponseEntity<List<Reserva>> findByFecha(@PathVariable("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicio,
			@PathVariable("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFin){
		
		try {
			
			List<Reserva> reservas = new ArrayList<Reserva>();
			reservas =reservaService.findByFechaIngresoBetween(fechaInicio, fechaFin);
			return new ResponseEntity<List<Reserva>>(reservas,HttpStatus.OK);
		
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Reserva>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value = "{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Reserva", notes ="Método para crear reserva")
	@ApiResponses({ 
		@ApiResponse(code=201,message = "Reserva creada"),
		@ApiResponse(code=400,message = "Solicitud de creacion invalida")		
	})
	public ResponseEntity<Reserva>insertReserva(@PathVariable("id") int id,@Valid @RequestBody Reserva reserva){
		try {
			Reserva reservaNew = new Reserva();
			Cliente cliente = clienteService.findById(id).get();
			reserva.setCliente(cliente);
			
			reservaNew = reservaService.save(reserva);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(reservaNew.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
}
