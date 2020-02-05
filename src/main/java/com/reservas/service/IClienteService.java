package com.reservas.service;

import java.util.List;

import com.reservas.entities.Cliente;

public interface IClienteService extends CrudService<Cliente> {
	
	List<Cliente> findByApellidos(String apellidos) throws Exception;
    Cliente findByDni(String dni) throws Exception;
   

}
