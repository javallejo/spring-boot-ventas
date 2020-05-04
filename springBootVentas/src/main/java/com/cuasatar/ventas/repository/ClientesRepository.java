package com.cuasatar.ventas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.dto.ClienteParaVender;
import com.cuasatar.ventas.entity.Cliente;


public interface ClientesRepository extends CrudRepository<Cliente, Long> {
	public Optional<Cliente> findByDni(String dni);
	
@Query("select new com.cuasatar.ventas.dto.ClienteParaVender(c.id) from  Cliente c inner join ClienteEstado ce on ce.clienteId=c.id inner join Estado e on e.id=ce.estadoId where e.descripcion='STATE_ACTIVE' order by c.nombres asc")
	public List<ClienteParaVender> fetchClientesEstadoActiveList();
	
}
