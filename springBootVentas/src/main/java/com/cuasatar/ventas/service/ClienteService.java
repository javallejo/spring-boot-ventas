package com.cuasatar.ventas.service;

import com.cuasatar.ventas.entity.Cliente;


public interface ClienteService {

	public Iterable<Cliente> getAllClients();
	public Cliente createClient(Cliente client) throws Exception;
	public Cliente getClientById(Long id) throws Exception;
	public Cliente updateClient(Cliente client) throws Exception;
	public void deleteClient(Long id) throws  Exception;
}
