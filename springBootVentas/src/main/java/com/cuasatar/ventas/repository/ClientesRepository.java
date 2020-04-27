package com.cuasatar.ventas.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.entity.Cliente;


public interface ClientesRepository extends CrudRepository<Cliente, Long> {
	public Optional<Cliente> findByDni(String dni);
}
