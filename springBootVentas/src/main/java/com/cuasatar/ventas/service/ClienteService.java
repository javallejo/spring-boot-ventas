package com.cuasatar.ventas.service;

import java.util.List;

import com.cuasatar.ventas.dto.ClienteParaVender;
import com.cuasatar.ventas.dto.ClientesEstadoDTO;
import com.cuasatar.ventas.dto.UsuarioRolesDTO;
import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.Usuario;


public interface ClienteService {

	public Iterable<Cliente> getAllClients();
	public Cliente createClient(Cliente client) throws Exception;
	public Cliente getClientById(Long id) throws Exception;
	public Cliente getClientByDni(String dni) throws Exception;
	public Cliente updateClient(Cliente client) throws Exception;
	public void deleteClient(Long id) throws  Exception;
	
	public Iterable<Cliente> getClientListById(Iterable<Long> id);
	
	public List<ClienteParaVender> fetchClientesEstadoActiveList() throws Exception;
}
