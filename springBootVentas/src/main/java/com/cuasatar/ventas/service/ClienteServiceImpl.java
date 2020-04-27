package com.cuasatar.ventas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.repository.ClientesRepository;


@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired	
	ClientesRepository clienteRepository;

	@Override
	public Iterable<Cliente> getAllClients() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente createClient(Cliente client) throws Exception {

		if (checkDnilAvailable(client) ) {
			
		client = clienteRepository.save(client);
		}
		 
		 return client;
	}

	@Override
	public Cliente getClientById(Long id) throws Exception {

		return clienteRepository.findById(id).orElseThrow(() -> new Exception("El Id del cliente no existe."));

	}

	@Override
	public Cliente updateClient(Cliente client) throws Exception {

		return clienteRepository.save(client);
	}

	@Override
	public void deleteClient(Long id) throws Exception {
		Cliente client = getClientById(id);
		clienteRepository.delete(client);
		
	}
	
	private boolean checkDnilAvailable(Cliente client) throws Exception {
		Optional<Cliente> dniFound = clienteRepository.findByDni(client.getDni());
		if (dniFound.isPresent()) {
			throw new Exception("Dni no disponible");
		}
		return true;
	}

}
