package com.reservas.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.entities.Cliente;
import com.reservas.repository.IClienteRepository;
import com.reservas.service.IClienteService;


@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements IClienteService{
	
	
	@Autowired
	IClienteRepository clienteRepository;
	
	@Transactional
	@Override
	public Cliente save(Cliente t) throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.save(t);
	}
	
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

	
	@Override
	public Optional<Cliente> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id);
	}

	
	@Override
	public List<Cliente> findAll() throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	
	@Override
	public List<Cliente> findByApellidos(String apellidos) throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.findByApellidos(apellidos);
	}
	
	
	@Override
	public Cliente findByDni(String dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteRepository.findByDni(dni);
	}

}
